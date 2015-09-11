package classfile;
/**
4.5 The Constant Pool
Java virtual machine instructions do not rely on the runtime layout of classes,
interfaces, class instances, or arrays. Instead, instructions refer to symbolic information
in the constant_pool table.
All constant_pool table entries have the following general format:
cp_info {
u1 tag;
u1 info[];
}
Each item in the constant_pool table must begin with a 1-byte tag indicating the
kind of cp_info entry. The contents of the info array vary with the value of tag.
The valid tags and their values are listed in Table 4.3. Each tag byte must be followed
by two or more bytes giving information about the specific constant. The format
of the additional information varies with the tag value.
Table 4.3 Constant pool tags
Constant Type Value
CONSTANT_Class 7
CONSTANT_Fieldref 9
CONSTANT_Methodref 10
CONSTANT_InterfaceMethodref 11
CONSTANT_String 8
CONSTANT_Integer 3
CONSTANT_Float 4
CONSTANT_Long 5
CONSTANT_Double 6
CONSTANT_NameAndType 12
CONSTANT_Utf8 1

4.5.1 The CONSTANT_Class_info Structure
The CONSTANT_Class_info structure is used to represent a class or an interface:
CONSTANT_Class_info {
u1 tag;
u2 name_index;
}

*/
public abstract class CpInfo {

    public final static byte CLASS = 7;
    public final static byte FIELDREF = 9;
    public final static byte METHODREF = 10;
    public final static byte INTERFACEMETHODREF = 11;
    public final static byte STRING = 8;
    public final static byte INTEGER = 3;
    public final static byte FLOAT = 4;
    public final static byte LONG = 5;
    public final static byte DOUBLE = 6;
    public final static byte NAMEANDTYPE = 12;
    public final static byte UTF8 = 1;
    public final static String[] tagNames = {
        null, "Utf8Info", null, "IntegerInfo", "FloatInfo", "LongInfo", "DoubleInfo", "ClassInfo", "StringInfo", "FieldrefInfo", "MethodrefInfo", "InterfaceMethodref", "NameAndTypeInfo"
    };

    public byte tag;
    //byte info[];

    public static final String toName(byte tag) {
        return tag>=0 && tag<tagNames.length ? tagNames[tag] : null;
    }
}
