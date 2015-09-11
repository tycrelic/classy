package classfile.attribute;

import classfile.*;
/**
4.8.10 The SourceFile Attribute
The SourceFile attribute is an optional fixed-length attribute in the attributes
table of the ClassFile (§4.2) structure. There can be no more than one
SourceFile attribute in the attributes table of a given ClassFile structure.
The SourceFile attribute has the following format:
SourceFile_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 sourcefile_index;
}
The items of the SourceFile_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing the string "SourceFile".
attribute_length
The value of the attribute_length item of a
SourceFile_attribute structure must be 2.

 */
public class SourceFileAttribute extends AttributeInfo {
    {
        attributeLength = 2;
    }

    /**
The value of the sourcefile_index item must be a valid index
into the constant_pool table. The constant pool entry at that
index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing a string.
The string referenced by the sourcefile_index item will be
interpreted as indicating the name of the source file from which
this class file was compiled. It will not be interpreted as
indicating the name of a directory containing the file or an
absolute path name for the file; such platform-specific additional
information must be supplied by the runtime interpreter or
development tool at the time the file name is actually used.
     */
    public short sourceFileIndex;
}