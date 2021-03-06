package util;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.*;

public class OpcodeInputFrame extends javax.swing.JFrame {

    /** Creates new form OpcodeInputFrame */
    public OpcodeInputFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        tfOperation = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        taFormat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        tfOperandStack = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        taForms = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        taLinkingExceptions = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        taRuntimeExceptions = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        taNotes = new javax.swing.JTextArea();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        btnParseText = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        taParseText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name:");

        jLabel2.setText("Operation:");

        jLabel3.setText("Format:");

        jLabel4.setText("Forms:");

        jLabel5.setText("Operand Stack:");

        jLabel6.setText("Description:");

        jLabel7.setText("Linking Exceptions:");

        jLabel8.setText("Runtime Exceptions:");

        jLabel9.setText("Notes:");

        taFormat.setColumns(20);
        taFormat.setRows(5);
        jScrollPane1.setViewportView(taFormat);

        taDescription.setColumns(20);
        taDescription.setRows(5);
        jScrollPane2.setViewportView(taDescription);

        taForms.setColumns(20);
        taForms.setRows(5);
        jScrollPane3.setViewportView(taForms);

        taLinkingExceptions.setColumns(20);
        taLinkingExceptions.setRows(5);
        jScrollPane4.setViewportView(taLinkingExceptions);

        taRuntimeExceptions.setColumns(20);
        taRuntimeExceptions.setRows(5);
        jScrollPane5.setViewportView(taRuntimeExceptions);

        taNotes.setColumns(20);
        taNotes.setRows(5);
        jScrollPane6.setViewportView(taNotes);

        btnLast.setText("<");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        btnParseText.setText("Parse text");
        btnParseText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParseTextActionPerformed(evt);
            }
        });

        taParseText.setColumns(20);
        taParseText.setRows(5);
        jScrollPane7.setViewportView(taParseText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane3)
                                            .addComponent(jScrollPane1)
                                            .addComponent(tfOperation)
                                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                            .addComponent(tfOperandStack, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnParseText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tfOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfOperandStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnLast)
                    .addComponent(btnLoad)
                    .addComponent(btnSave)
                    .addComponent(btnParseText))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        updateOpcodeDef(idx);
        --idx;
        displayOpcodeDef(idx);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        updateOpcodeDef(idx);
        ++idx;
        displayOpcodeDef(idx);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnParseTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParseTextActionPerformed
        try {
            parseFFText();
        } catch (IOException ex) {
            Logger.getLogger(OpcodeInputFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        idx = 0;
        displayOpcodeDef(idx);
    }//GEN-LAST:event_btnParseTextActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        updateOpcodeDef(idx);
        try {
            save(opcodeDefs);
        } catch (IOException ex) {
            Logger.getLogger(OpcodeInputFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(OpcodeInputFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        try {
            load();
        } catch (XMLStreamException ex) {
            Logger.getLogger(OpcodeInputFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpcodeInputFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        idx = 0;
        //for(int i=0, len=opcodeDefs.size(); i<len; ++i) {
        displayOpcodeDef(idx);
        //}
    }//GEN-LAST:event_btnLoadActionPerformed

    public void displayOpcodeDef(int idx) {
        if (idx <= 0) {
            btnLast.setEnabled(false);
        } else {
            btnLast.setEnabled(true);
        }

        if (idx >= 0) {
            String[] opcodeDef;
            if (idx < opcodeDefs.size()) {
                opcodeDef = opcodeDefs.get(idx);
            } else {
                opcodeDef = new String[9];
                opcodeDefs.add(opcodeDef);
            }
            tfName.setText(opcodeDef[0].trim());
            tfOperation.setText(opcodeDef[1].trim());
            taFormat.setText(opcodeDef[2].trim());
            taForms.setText(opcodeDef[3].trim());
            tfOperandStack.setText(opcodeDef[4].trim());
            taDescription.setText(opcodeDef[5].trim());
            taLinkingExceptions.setText(opcodeDef[6].trim());
            taRuntimeExceptions.setText(opcodeDef[7].trim());
            taNotes.setText(opcodeDef[8].trim());
        }
    }

    public void updateOpcodeDef(int idx) {
        if (idx >= 0) {
            String[] opcodeDef;
            if (idx < opcodeDefs.size()) {
                opcodeDef = opcodeDefs.get(idx);
            } else {
                opcodeDef = new String[9];
                opcodeDefs.add(opcodeDef);
            }
            opcodeDef[0] = tfName.getText().trim();
            opcodeDef[1] = tfOperation.getText().trim();
            opcodeDef[2] = taFormat.getText().trim();
            opcodeDef[3] = taForms.getText().trim();
            opcodeDef[4] = tfOperandStack.getText().trim();
            opcodeDef[5] = taDescription.getText().trim();
            opcodeDef[6] = taLinkingExceptions.getText().trim();
            opcodeDef[7] = taRuntimeExceptions.getText().trim();
            opcodeDef[8] = taNotes.getText();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new OpcodeInputFrame().setVisible(true);
            }
        });
    }
    List<String[]> opcodeDefs = new ArrayList<String[]>(256);
    //String[] opcodeDef;
    int idx;

    public void save(List<String[]> opcodeDefs) throws IOException, XMLStreamException {
        XMLStreamWriter xsw = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream("opcodes.xml"));
        xsw.writeStartElement("opcodes");

        for (String[] opcodeDef : opcodeDefs) {

            xsw.writeStartElement("opcode");
            xsw.writeStartElement("name");
            xsw.writeCharacters(opcodeDef[0]);
            xsw.writeEndElement();

            xsw.writeStartElement("operation");
            xsw.writeCharacters(opcodeDef[1]);
            xsw.writeEndElement();

            xsw.writeStartElement("format");
            xsw.writeCharacters(opcodeDef[2]);
            xsw.writeEndElement();

            xsw.writeStartElement("forms");
            xsw.writeCharacters(opcodeDef[3]);
            xsw.writeEndElement();

            xsw.writeStartElement("operandStack");
            xsw.writeCharacters(opcodeDef[4]);
            xsw.writeEndElement();

            xsw.writeStartElement("description");
            xsw.writeCharacters(opcodeDef[5]);
            xsw.writeEndElement();

            xsw.writeStartElement("linkingExceptions");
            xsw.writeCharacters(opcodeDef[6]);
            xsw.writeEndElement();

            xsw.writeStartElement("runtimeExceptions");
            xsw.writeCharacters(opcodeDef[7]);
            xsw.writeEndElement();

            xsw.writeStartElement("notes");
            xsw.writeCharacters(opcodeDef[8]);
            xsw.writeEndElement();

            xsw.writeEndElement();
        }
        xsw.writeEndElement();
        xsw.close();
    }

    public void load() throws XMLStreamException, IOException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream("opcodes.xml"));
        opcodeDefs.clear();
        String[] opcodeDef = null;
        StringBuilder buf = new StringBuilder();
        int attrIdx = -1;
        for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    String tagName = parser.getLocalName();
                    if (tagName.equals("opcodes")) {
                    } else if (tagName.equals("opcode")) {
                        opcodeDef = new String[9];
                        opcodeDefs.add(opcodeDef);
                    } else if (tagName.equals("name")) {
                        attrIdx = 0;
                    } else if (tagName.equals("operation")) {
                        attrIdx = 1;
                    } else if (tagName.equals("format")) {
                        attrIdx = 2;
                    } else if (tagName.equals("forms")) {
                        attrIdx = 3;
                    } else if (tagName.equals("operandStack")) {
                        attrIdx = 4;
                    } else if (tagName.equals("description")) {
                        attrIdx = 5;
                    } else if (tagName.equals("linkingExceptions")) {
                        attrIdx = 6;
                    } else if (tagName.equals("runtimeExceptions")) {
                        attrIdx = 7;
                    } else if (tagName.equals("notes")) {
                        attrIdx = 8;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (opcodeDef != null && attrIdx >= 0 && attrIdx < opcodeDef.length) {
                        opcodeDef[attrIdx] = buf.toString();
                    }
                    buf.setLength(0);
                    attrIdx = -1;
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (opcodeDef != null && attrIdx >= 0 && attrIdx < opcodeDef.length) {
                        buf.append(parser.getText());
                    }
                    break;
                case XMLStreamConstants.CDATA:
                    if (opcodeDef != null && attrIdx >= 0 && attrIdx < opcodeDef.length) {
                        buf.append(parser.getText());
                    }
                    break;
            }
        }
        parser.close();
    }

    public void parseFFText() throws IOException {
        opcodeDefs.clear();
        String txt = taParseText.getText();
        BufferedReader br = new BufferedReader(new StringReader(txt));
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.startsWith(" ")) {
                String[] opcodeDef = new String[9];
                opcodeDef[0] = line.trim();
                StringBuilder buf = new StringBuilder();
                int attrIdx = -1;
                while ((line = br.readLine()) != null) {
                   String trimmedLine = line.trim();
                    if (!line.startsWith(" ") && trimmedLine.length()>0) {
                        if(attrIdx>=0 && attrIdx<opcodeDef.length) {
                        opcodeDef[attrIdx] = buf.toString();
                        buf.setLength(0);

                        }
                        String attributeName = line.trim();
                        if (attributeName.equals("Operation")) {
                            attrIdx = 1;
                        } else if (attributeName.equals("Format")) {
                            attrIdx = 2;
                        } else if (attributeName.equals("Forms")) {
                            attrIdx = 3;
                        } else if (attributeName.equals("Operand Stack")) {
                            attrIdx = 4;
                        } else if (attributeName.equals("Description")) {
                            attrIdx = 5;
                        } else if (attributeName.equals("Linking Exceptions")) {
                            attrIdx = 6;
                        } else if (attributeName.equals("Runtime Exceptions")) {
                            attrIdx = 7;
                        } else if (attributeName.equals("Notes")) {
                            attrIdx = 8;
                        } else {
                            throw new IOException("Unknown attribute:" + attributeName);
                        }
                    } else {
                        String attributeValue = line.trim();
                        if (buf.length() > 0) {
                            buf.append("\r\n");
                        }
                        buf.append(attributeValue);
                    }
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnParseText;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextArea taFormat;
    private javax.swing.JTextArea taForms;
    private javax.swing.JTextArea taLinkingExceptions;
    private javax.swing.JTextArea taNotes;
    private javax.swing.JTextArea taParseText;
    private javax.swing.JTextArea taRuntimeExceptions;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfOperandStack;
    private javax.swing.JTextField tfOperation;
    // End of variables declaration//GEN-END:variables
}
