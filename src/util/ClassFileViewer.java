package util;

import classfile.*;
import classfile.attribute.*;
import classfile.constant.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

public class ClassFileViewer extends javax.swing.JFrame {

    /** Creates new form NewJFrame */
    public ClassFileViewer() {
        initComponents();
        //tfClassFileName.setText("build\\classes\\apackage\\bpackage\\MutiPackageEmptyClass.class");
        //display(tfClassFileName.getText());

        
        cbbFileList.addItem("build\\classes");
        String fn = (String) cbbFileList.getItemAt(0);
        listFiles(fn);
    }

    public void display(String classFileName) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(classFileName));
            ClassFile cf = ClassReader.read(dis);
            this.display(cf);
        } catch (IOException ex) {
            Logger.getLogger(ClassFileViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void display(ClassFile cf) {
        tfMagic.setText(Integer.toHexString(cf.magic));
        tfMinorVersion.setText(Integer.toString(cf.minorVersion));
        tfMajorVersion.setText(Integer.toString(cf.majorVersion));
        tfConstantPoolCount.setText(Integer.toString(cf.constantPoolCount));
        displayConstantPool(cf);

        tfAccessFlags.setText(Integer.toString(cf.accessFlags));
        tfAccessText.setText(ClassDecompiler.getClassAccessText(cf.accessFlags));

        tfThisClass.setText(Integer.toHexString(0xffff & cf.thisClass));
        tfThisClassName.setText(ClassDecompiler.getFullyQualifiedClassName(cf, cf.thisClass));

        tfSuperClass.setText(Integer.toHexString(0xffff & cf.superClass));
        tfSuperClassName.setText(ClassDecompiler.getFullyQualifiedClassName(cf, cf.superClass));

        displayInterfaces(cf);
        displayFields(cf);
        displayMethods(cf);
        displayAttributes(cf);
        displaySourceCode(cf);
    }

    public void displaySourceCode(ClassFile cf) {
        StringWriter sw = new StringWriter();
        ClassDecompiler.decompile(cf, new PrintWriter(sw));
        StringBuffer buf = sw.getBuffer();
        taSourceCode.setText(buf.toString());
    }

    public void displayConstantPool(ClassFile cf) {
        DefaultTableModel dtmConstantPool = (DefaultTableModel) tblConstantPool.getModel();
        dtmConstantPool.setRowCount(0);
        CpInfo[] cp = cf.constantPool;
        for (int i = 0, count = cp.length; i < count; ++i) {
            CpInfo info = cp[i];
            String infoText = null;
            switch (info.tag) {
                case CpInfo.CLASS:
                    infoText = Integer.toHexString(((ClassInfo) info).nameIndex);
                    break;
                case CpInfo.FIELDREF:
                    infoText = "classIndex: " + Integer.toHexString(((FieldrefInfo) info).classIndex) + ", nameAndTypeIndex: " + Integer.toHexString(((FieldrefInfo) info).nameAndTypeIndex);
                    break;
                case CpInfo.METHODREF:
                    infoText = "classIndex: " + Integer.toHexString(((MethodrefInfo) info).classIndex) + ", nameAndTypeIndex: " + Integer.toHexString(((MethodrefInfo) info).nameAndTypeIndex);
                    break;
                case CpInfo.INTERFACEMETHODREF:
                    infoText = "classIndex: " + Integer.toHexString(((InterfaceMethodrefInfo) info).classIndex) + ", nameAndTypeIndex: " + Integer.toHexString(((InterfaceMethodrefInfo) info).nameAndTypeIndex);
                    break;
                case CpInfo.STRING:
                    infoText = Integer.toHexString(((StringInfo) info).stringIndex);
                    break;
                case CpInfo.INTEGER:
                    infoText = Integer.toHexString(((IntegerInfo) info).bytes);
                    break;
                case CpInfo.FLOAT:
                    infoText = Integer.toHexString(((FloatInfo) info).bytes);
                    break;
                case CpInfo.LONG:
                    infoText = "highBytes: " + Integer.toHexString(((LongInfo) info).highBytes) + ", lowBytes: " + Integer.toHexString(((LongInfo) info).lowBytes);
                    break;
                case CpInfo.DOUBLE:
                    infoText = "highBytes: " + Integer.toHexString(((DoubleInfo) info).highBytes) + ", lowBytes: " + Integer.toHexString(((DoubleInfo) info).lowBytes);
                    break;
                case CpInfo.NAMEANDTYPE:
                    infoText = "nameIndex: " + Integer.toHexString(((NameAndTypeInfo) info).nameIndex) + ", descriptorIndex: " + Integer.toHexString(((NameAndTypeInfo) info).descriptorIndex);
                    break;
                case CpInfo.UTF8:
                    infoText = new String(((Utf8Info) info).bytes);
                    break;
            }
            dtmConstantPool.addRow(new Object[]{Integer.toHexString(i + 1), Integer.toHexString(info.tag), CpInfo.toName(info.tag), infoText, null});
        }
    }

    public void displayInterfaces(ClassFile cf) {
        tfInterfacesCount.setText(Integer.toString(cf.interfacesCount));
        DefaultTableModel dtmInterfaces = (DefaultTableModel) tblInterfaces.getModel();
        dtmInterfaces.setRowCount(0);
        short[] interfaces = cf.interfaces;
        for (int i = 0, count = interfaces.length; i < count; ++i) {
            short ciIndex = interfaces[i];
            ClassInfo ci = (ClassInfo) cf.getCpInfo(ciIndex);
            Utf8Info ui = (Utf8Info) cf.getCpInfo(ci.nameIndex);
            String interfaceName = new String(ui.bytes);
            dtmInterfaces.addRow(new Object[]{Integer.toHexString(ciIndex), interfaceName});
        }
    }

    public void displayFields(ClassFile cf) {
        tfFieldsCount.setText(Integer.toString(cf.fieldsCount));
        DefaultTableModel dtmFields = (DefaultTableModel) tblFields.getModel();
        dtmFields.setRowCount(0);
        FieldInfo[] fields = cf.fields;
        StringBuilder buf = new StringBuilder();
        for (int i = 0, count = fields.length; i < count; ++i) {
            FieldInfo fi = fields[i];
            try {
                DebugUtil.appendObjectText(buf, fi.attributes, 0);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ClassFileViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            String infoText = buf.toString();
            dtmFields.addRow(new Object[]{ClassDecompiler.getFieldAccessText(fi.accessFlags), Integer.toHexString(fi.nameIndex), Integer.toHexString(fi.descriptorIndex), Integer.toString(fi.attributesCount), infoText});

            ExportColumn exportColumn = new ExportColumn(cf, 0);
            TableColumn tcExport = tblFields.getColumn("Export");
            tcExport.setCellRenderer(exportColumn);
            tcExport.setCellEditor(exportColumn);
        }
    }

    public void displayMethods(ClassFile cf) {
        tfMethodsCount.setText(Integer.toString(cf.methodsCount));
        DefaultTableModel dtmMethods = (DefaultTableModel) tblMethods.getModel();
        dtmMethods.setRowCount(0);
        MethodInfo[] methods = cf.methods;
        StringBuilder buf = new StringBuilder();
        for (int i = 0, count = methods.length; i < count; ++i) {
            MethodInfo mi = methods[i];
            try {
                DebugUtil.appendObjectText(buf, mi.attributes, 0);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ClassFileViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            String infoText = buf.toString();
            dtmMethods.addRow(new Object[]{ClassDecompiler.getMethodAccessText(mi.accessFlags), Integer.toHexString(mi.nameIndex), Integer.toHexString(mi.descriptorIndex), Integer.toString(mi.attributesCount), infoText});

            ExportColumn exportColumn = new ExportColumn(cf, 1);
            TableColumn tcExport = tblMethods.getColumn("Export");
            tcExport.setCellRenderer(exportColumn);
            tcExport.setCellEditor(exportColumn);
        }
    }

    public void displayAttributes(ClassFile cf) {
        tfAttributesCount.setText(Integer.toString(cf.attributesCount));
        DefaultTableModel dtmAttributes = (DefaultTableModel) tblAttributes.getModel();
        dtmAttributes.setRowCount(0);
        AttributeInfo[] attributes = cf.attributes;

        StringBuilder buf = new StringBuilder();
        for (int i = 0, count = attributes.length; i < count; ++i) {
            AttributeInfo attrInfo = attributes[i];
            try {
                DebugUtil.appendObjectText(buf, attrInfo, 0);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ClassFileViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
            String infoText = buf.toString();
            dtmAttributes.addRow(new Object[]{Integer.toHexString(0xffff & attrInfo.attributeNameIndex), Integer.toString(attrInfo.attributeLength), infoText});
            buf.setLength(0);

            ExportColumn exportColumn = new ExportColumn(cf, 2);
            TableColumn tcExport = tblAttributes.getColumn("Export");
            tcExport.setCellRenderer(exportColumn);
            tcExport.setCellEditor(exportColumn);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConstantPool = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        tfConstantPoolCount = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInterfaces = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        tfInterfacesCount = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        tfFieldsCount = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFields = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        tfMethodsCount = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMethods = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        tfAttributesCount = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAttributes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        taSourceCode = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        tfMagic = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfMinorVersion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfMajorVersion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfAccessFlags = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfThisClass = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfSuperClass = new javax.swing.JTextField();
        tfAccessText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfClassFileName = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnDisplay = new javax.swing.JButton();
        tfThisClassName = new javax.swing.JTextField();
        tfSuperClassName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnUp = new javax.swing.JButton();
        cbbFileList = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Class file viewer");

        tblConstantPool.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "index", "tag", "type", "info", "export"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblConstantPool);

        jLabel4.setText("constant pool count");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(tfConstantPoolCount, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfConstantPoolCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Constant pool", jPanel2);

        tblInterfaces.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ClassInfo index", "Interface name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblInterfaces);

        jLabel9.setText("interface count");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(50, 50, 50)
                        .addComponent(tfInterfacesCount, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfInterfacesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Interfaces", jPanel3);

        jLabel11.setText("fields count");

        tblFields.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "access flags", "name index", "descriptor index", "attributes count", "attributes info", "Export"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblFields);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(65, 65, 65)
                        .addComponent(tfFieldsCount, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfFieldsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fields", jPanel4);

        jLabel12.setText("methods count");

        tblMethods.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "access flags", "name index", "descriptor index", "attributes count", "attributes info", "Export"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblMethods);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(65, 65, 65)
                        .addComponent(tfMethodsCount, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfMethodsCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Methods", jPanel5);

        jLabel13.setText("attributes count");

        tblAttributes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attribute name index", "Attribute length", "info", "Export"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblAttributes);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(65, 65, 65)
                        .addComponent(tfAttributesCount, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfAttributesCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Attributes", jPanel6);

        taSourceCode.setColumns(20);
        taSourceCode.setRows(5);
        jScrollPane6.setViewportView(taSourceCode);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Source code", jPanel1);

        jLabel1.setText("magic");

        jLabel2.setText("minor version");

        jLabel3.setText("major version");

        jLabel6.setText("access flags");

        jLabel7.setText("this class");

        jLabel8.setText("super class");

        tfAccessText.setBackground(new java.awt.Color(204, 255, 255));

        jLabel5.setText("class file name: ");

        btnDisplay.setText("Display");
        btnDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayActionPerformed(evt);
            }
        });

        tfThisClassName.setBackground(new java.awt.Color(204, 255, 255));

        tfSuperClassName.setBackground(new java.awt.Color(204, 255, 255));

        jLabel10.setText("file list:");

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnUp.setText("Up");
        btnUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpActionPerformed(evt);
            }
        });

        cbbFileList.setEditable(true);
        cbbFileList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbFileListItemStateChanged(evt);
            }
        });
        cbbFileList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFileListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfMajorVersion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addComponent(tfMinorVersion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addComponent(tfMagic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfSuperClass, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfThisClass, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAccessFlags, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAccessText, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                                    .addComponent(tfThisClassName, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                                    .addComponent(tfSuperClassName, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbbFileList, 0, 662, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tfClassFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisplay)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(btnNext)
                    .addComponent(btnUp)
                    .addComponent(cbbFileList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfClassFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisplay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfMagic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfMinorVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfMajorVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfAccessFlags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAccessText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfThisClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfThisClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfSuperClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSuperClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayActionPerformed
        display(tfClassFileName.getText());
    }//GEN-LAST:event_btnDisplayActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        int i = cbbFileList.getSelectedIndex();
        int count = cbbFileList.getItemCount();
        if (i < count) {
            cbbFileList.setSelectedIndex(i + 1);
            //cbbFileList.getItemAt(i+1);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpActionPerformed

    private void cbbFileListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbFileListItemStateChanged
        //System.out.println(evt.getItem().getClass());
        //System.out.println(evt.paramString());
        String fn = (String) evt.getItem();
        listFiles(fn);
    }//GEN-LAST:event_cbbFileListItemStateChanged

    private void cbbFileListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFileListActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbFileListActionPerformed
    public void listFiles(String fn) {
        File f = new File(fn);
        if (f.isDirectory()) {
            cbbFileList.removeAllItems();
            File[] fa = f.listFiles();
            for (File ff : fa) {
                String path = ff.getPath();
                if (ff.isDirectory() || path.endsWith(".class")) {
                    cbbFileList.addItem(path);
                }
            }
        } else if (f.isFile()) {
            tfClassFileName.setText(f.getPath());
            display(tfClassFileName.getText());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ClassFileViewer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisplay;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUp;
    private javax.swing.JComboBox cbbFileList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea taSourceCode;
    private javax.swing.JTable tblAttributes;
    private javax.swing.JTable tblConstantPool;
    private javax.swing.JTable tblFields;
    private javax.swing.JTable tblInterfaces;
    private javax.swing.JTable tblMethods;
    private javax.swing.JTextField tfAccessFlags;
    private javax.swing.JTextField tfAccessText;
    private javax.swing.JTextField tfAttributesCount;
    private javax.swing.JTextField tfClassFileName;
    private javax.swing.JTextField tfConstantPoolCount;
    private javax.swing.JTextField tfFieldsCount;
    private javax.swing.JTextField tfInterfacesCount;
    private javax.swing.JTextField tfMagic;
    private javax.swing.JTextField tfMajorVersion;
    private javax.swing.JTextField tfMethodsCount;
    private javax.swing.JTextField tfMinorVersion;
    private javax.swing.JTextField tfSuperClass;
    private javax.swing.JTextField tfSuperClassName;
    private javax.swing.JTextField tfThisClass;
    private javax.swing.JTextField tfThisClassName;
    // End of variables declaration//GEN-END:variables
}
