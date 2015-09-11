package classfile;

import classfile.attribute.*;
import classfile.constant.*;
import java.io.*;

public class ClassReader {

    public static ClassFile read(DataInputStream dis) throws IOException {
        ClassFile cf = new ClassFile();
        cf.magic = dis.readInt();
        cf.minorVersion = (short) dis.readUnsignedShort();
        cf.majorVersion = (short) dis.readUnsignedShort();
        cf.constantPoolCount = (short) dis.readUnsignedShort();
        cf.constantPool = readConstantPool(dis, cf.constantPoolCount);

        cf.accessFlags = (short) dis.readUnsignedShort();
        cf.thisClass = (short) dis.readUnsignedShort();
        cf.superClass = (short) dis.readUnsignedShort();
        cf.interfacesCount = (short) dis.readUnsignedShort();

        short[] interfaces = new short[0xffff & cf.interfacesCount];
        for (int i = 0, count = interfaces.length; i < count; ++i) {
            interfaces[i] = (short) dis.readUnsignedShort();
        }
        cf.interfaces = interfaces;

        cf.fieldsCount = (short) dis.readUnsignedShort();
        cf.fields = readFields(dis, cf.fieldsCount, cf.constantPool);

        cf.methodsCount = (short) dis.readUnsignedShort();
        cf.methods = readMethods(dis, cf.methodsCount, cf.constantPool);

        cf.attributesCount = (short) dis.readUnsignedShort();
        cf.attributes = readAttributes(dis, cf.attributesCount, cf.constantPool);
        return cf;
    }

    public static CpInfo[] readConstantPool(DataInputStream dis, short constantPoolCount) throws IOException {
        CpInfo[] cp = new CpInfo[(0xffff & constantPoolCount) - 1];
        for (int i = 0, count = cp.length; i < count; ++i) {
            byte tag = (byte) dis.read();
            switch (tag) {
                case CpInfo.CLASS:
                    ClassInfo ci = new ClassInfo();
                    cp[i] = ci;
                    ci.nameIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.FIELDREF:
                    FieldrefInfo fi = new FieldrefInfo();
                    cp[i] = fi;
                    fi.classIndex = (short) dis.readUnsignedShort();
                    fi.nameAndTypeIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.METHODREF:
                    MethodrefInfo mi = new MethodrefInfo();
                    cp[i] = mi;
                    mi.classIndex = (short) dis.readUnsignedShort();
                    mi.nameAndTypeIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.INTERFACEMETHODREF:
                    InterfaceMethodrefInfo imi = new InterfaceMethodrefInfo();
                    cp[i] = imi;
                    imi.classIndex = (short) dis.readUnsignedShort();
                    imi.nameAndTypeIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.STRING:
                    StringInfo si = new StringInfo();
                    cp[i] = si;
                    si.stringIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.INTEGER:
                    IntegerInfo ii = new IntegerInfo();
                    cp[i] = ii;
                    ii.bytes = dis.readInt();
                    break;
                case CpInfo.FLOAT:
                    FloatInfo fli = new FloatInfo();
                    cp[i] = fli;
                    fli.bytes = dis.readInt();
                    break;
                case CpInfo.LONG:
                    LongInfo li = new LongInfo();
                    cp[i] = li;
                    cp[++i] = li; //TODO: long takes 2 constant pool entries
                    li.highBytes = dis.readInt();
                    li.lowBytes = dis.readInt();
                    break;
                case CpInfo.DOUBLE:
                    DoubleInfo di = new DoubleInfo();
                    cp[i] = di;
                    cp[++i] = di; //TODO: double takes 2 constant pool entries
                    di.highBytes = dis.readInt();
                    di.lowBytes = dis.readInt();
                    break;
                case CpInfo.NAMEANDTYPE:
                    NameAndTypeInfo nati = new NameAndTypeInfo();
                    cp[i] = nati;
                    nati.nameIndex = (short) dis.readUnsignedShort();
                    nati.descriptorIndex = (short) dis.readUnsignedShort();
                    break;
                case CpInfo.UTF8:
                    Utf8Info ui = new Utf8Info();
                    cp[i] = ui;
                    ui.length = (short) dis.readUnsignedShort();
                    byte[] b = new byte[ui.length];
                    ui.bytes = b;
                    dis.read(b);
                    break;
            }
        }
        return cp;
    }

    public static FieldInfo[] readFields(DataInputStream dis, short fieldsCount, CpInfo[] cp) throws IOException {
        FieldInfo[] fields = new FieldInfo[0xffff & fieldsCount];
        for (int i = 0, count = fields.length; i < count; ++i) {
            FieldInfo fi = new FieldInfo();
            fields[i] = fi;
            fi.accessFlags = (short) dis.readUnsignedShort();
            fi.nameIndex = (short) dis.readUnsignedShort();
            fi.descriptorIndex = (short) dis.readUnsignedShort();
            fi.attributesCount = (short) dis.readUnsignedShort();
            fi.attributes = readAttributes(dis, fi.attributesCount, cp);
        }
        return fields;
    }

    public static MethodInfo[] readMethods(DataInputStream dis, short methodsCount, CpInfo[] cp) throws IOException {
        MethodInfo[] methods = new MethodInfo[0xffff & methodsCount];
        for (int i = 0, count = methods.length; i < count; ++i) {
            MethodInfo mi = new MethodInfo();
            methods[i] = mi;
            mi.accessFlags = (short) dis.readUnsignedShort();
            mi.nameIndex = (short) dis.readUnsignedShort();
            mi.descriptorIndex = (short) dis.readUnsignedShort();
            mi.attributesCount = (short) dis.readUnsignedShort();
            mi.attributes = readAttributes(dis, mi.attributesCount, cp);

        }
        return methods;
    }

    //TODO: read attributes correctly
    public static AttributeInfo[] readAttributes(DataInputStream dis, short attributesCount, CpInfo[] cp) throws IOException {
        AttributeInfo[] attributes = new AttributeInfo[0xffff & attributesCount];
        for (int i = 0; i < attributesCount; ++i) {
            short attributeNameIndex = (short) dis.readUnsignedShort();
            int attributeLength = dis.readInt();

            AttributeInfo attrInfo;
            String attributeName = new String(((Utf8Info) cp[(0xffff & attributeNameIndex) - 1]).bytes);

            if (AttributeInfo.SOURCE_FILE.equals(attributeName)) {
                SourceFileAttribute ai = new SourceFileAttribute();
                ai.sourceFileIndex = (short) dis.readUnsignedShort();
                attrInfo = ai;
            } else if (AttributeInfo.CONSTANT_VALUE.equals(attributeName)) {
                ConstantValueAttribute ai = new ConstantValueAttribute();
                ai.constantValueIndex = (short) dis.readUnsignedShort();
                attrInfo = ai;
            } else if (AttributeInfo.CODE.equals(attributeName)) {
                CodeAttribute ai = new CodeAttribute();
                ai.maxStack = (short) dis.readUnsignedShort();
                ai.maxLocals = (short) dis.readUnsignedShort();
                ai.codeLength = dis.readInt();
                ai.code = new byte[ai.codeLength];//TODO: decompile code
                dis.read(ai.code);
                ai.exceptionTableLength = (short) dis.readUnsignedShort();
                ai.exceptionTable = readExceptionTable(dis, ai.exceptionTableLength);
                ai.attributesCount = (short) dis.readUnsignedShort();
                ai.attributes = readAttributes(dis, ai.attributesCount, cp);
                attrInfo = ai;
            } else if (AttributeInfo.STACK_MAP_TABLE.equals(attributeName)) {
                StackMapTableAttribute ai = new StackMapTableAttribute();
                //TODO: StackMapTableAttribute
                attrInfo = ai;
            } else if (AttributeInfo.EXCEPTIONS.equals(attributeName)) {
                ExceptionsAttribute ai = new ExceptionsAttribute();
                ai.numberOfExceptions = (short) dis.readUnsignedShort();
                short[] exceptionIndexTable = new short[0xffff & ai.numberOfExceptions];
                for (int j = 0, len2 = exceptionIndexTable.length; j < len2; ++j) {
                    exceptionIndexTable[j] = (short) dis.readUnsignedShort();
                }
                ai.exceptionIndexTable = exceptionIndexTable;
                attrInfo = ai;
            } else if (AttributeInfo.INNER_CLASSES.equals(attributeName)) {
                InnerClassesAttribute ai = new InnerClassesAttribute();
                ai.numberOfClasses = (short) dis.readUnsignedShort();
                InnerClassesAttribute.ClassEntry[] classes = new InnerClassesAttribute.ClassEntry[ai.numberOfClasses];
                for (int j = 0, len2 = classes.length; j < len2; ++j) {
                    InnerClassesAttribute.ClassEntry ce = new InnerClassesAttribute.ClassEntry();
                    ce.innerClassInfoIndex = (short) dis.readUnsignedShort();
                    ce.outerClassInfoIndex = (short) dis.readUnsignedShort();
                    ce.innerNameIndex = (short) dis.readUnsignedShort();
                    ce.innerClassAccessFlags = (short) dis.readUnsignedShort();
                    classes[j] = ce;
                }
                ai.classes = classes;
                attrInfo = ai;
            } else if (AttributeInfo.ENCLOSING_METHOD.equals(attributeName)) {
                EnclosingMethodAttribute ai = new EnclosingMethodAttribute();
                ai.classIndex = (short) dis.readUnsignedShort();
                ai.methodIndex = (short) dis.readUnsignedShort();
                attrInfo = ai;
            } else if (AttributeInfo.SYNTHETIC.equals(attributeName)) {
                SyntheticAttribute ai = new SyntheticAttribute();
                attrInfo = ai;
            } else if (AttributeInfo.SIGNATURE.equals(attributeName)) {
                SignatureAttribute ai = new SignatureAttribute();
                ai.signatureIndex = (short) dis.readUnsignedShort();
                attrInfo = ai;
            } else if (AttributeInfo.LINE_NUMBER_TABLE.equals(attributeName)) {
                LineNumberTableAttribute ai = new LineNumberTableAttribute();
                ai.lineNumberTableLength = (short) dis.readUnsignedShort();
                LineNumberTableAttribute.LineNumberEntry[] lineNumberTable = new LineNumberTableAttribute.LineNumberEntry[ai.lineNumberTableLength];
                for (int j = 0, len2 = lineNumberTable.length; j < len2; ++j) {
                    LineNumberTableAttribute.LineNumberEntry lne = new LineNumberTableAttribute.LineNumberEntry();
                    lne.startPc = (short) dis.readUnsignedShort();
                    lne.lineNumber = (short) dis.readUnsignedShort();
                    lineNumberTable[j] = lne;
                }
                ai.lineNumberTable = lineNumberTable;
                attrInfo = ai;
            } else if (AttributeInfo.LOCAL_VARIABLE_TABLE.equals(attributeName)) {
                LocalVariableTableAttribute ai = new LocalVariableTableAttribute();
                ai.localVariableTableLength = (short) dis.readUnsignedShort();
                ai.localVariableTable = readLocalVariableTable(dis, ai.localVariableTableLength);
                attrInfo = ai;
            } else if (AttributeInfo.DEPRECATED.equals(attributeName)) {
                DeprecatedAttribute ai = new DeprecatedAttribute();
                attrInfo = ai;
            } else {
                AttributeInfo ai = new AttributeInfo();
                byte[] info = new byte[(int) (0xffffffffL & attributeLength)];
                dis.read(info);
                ai.info = info;
                attrInfo = ai;
            }
            attrInfo.attributeNameIndex = attributeNameIndex;
            attrInfo.attributeLength = attributeLength;
            attributes[i] = attrInfo;
        }
        return attributes;
    }

    public static CodeAttribute.ExceptionHandler[] readExceptionTable(DataInputStream dis, int exceptionTableLength) throws IOException {
        CodeAttribute.ExceptionHandler[] exceptionTable = new CodeAttribute.ExceptionHandler[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; ++i) {
            CodeAttribute.ExceptionHandler eh = new CodeAttribute.ExceptionHandler();
            eh.startPc = (short) dis.readUnsignedShort();
            eh.endPc = (short) dis.readUnsignedShort();
            eh.handlerPc = (short) dis.readUnsignedShort();
            eh.catchType = (short) dis.readUnsignedShort();
            exceptionTable[i] = eh;
        }
        return exceptionTable;
    }

    public static LocalVariableTableAttribute.LocalVariable[] readLocalVariableTable(DataInputStream dis, int localVariableTableLength) throws IOException {
        LocalVariableTableAttribute.LocalVariable[] localVariableTable = new LocalVariableTableAttribute.LocalVariable[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; ++i) {
            LocalVariableTableAttribute.LocalVariable lv = new LocalVariableTableAttribute.LocalVariable();
            lv.startPc = (short) dis.readUnsignedShort();
            lv.length = (short) dis.readUnsignedShort();
            lv.nameIndex = (short) dis.readUnsignedShort();
            lv.descriptorIndex = (short) dis.readUnsignedShort();
            lv.index = (short) dis.readUnsignedShort();
            localVariableTable[i] = lv;
        }
        return localVariableTable;
    }

    public static AttributeInfo[] readAttributes2(DataInputStream dis, short attributesCount, CpInfo[] cp) throws IOException {
        AttributeInfo[] attributes = new AttributeInfo[0xffff & attributesCount];
        for (int i = 0; i < attributesCount; ++i) {
            AttributeInfo ai = new AttributeInfo();
            attributes[i] = ai;
            ai.attributeNameIndex = (short) dis.readUnsignedShort();
            ai.attributeLength = dis.readInt();

            //// //todo
            byte[] info = new byte[(int) (0xffffffffL & ai.attributeLength)];
            dis.read(info);
        }
        return attributes;
    }
}
