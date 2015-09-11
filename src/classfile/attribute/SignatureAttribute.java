package classfile.attribute;

import classfile.*;

/**
4.8.9 The Signature Attribute
The Signature attribute is an optional fixed-length attribute in the attributes
table of the ClassFile (§4.2), field_info(§4.6) and method_info (§4.7) structures.
The Signature attribute has the following format:
Signature_attribute {
u2 attribute_name_index;
u4 attribute_length;
u2 signature_index;
}
The items of the Signature_attribute structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid
index into the constant_pool table. The constant_pool entry
at that index must be a CONSTANT_Utf8_info(§4.5.7) structure
representing the string "Signature".
attribute_length
The value of the attribute_length item of a
Signature_attribute structure must be 2.
 */
public class SignatureAttribute extends AttributeInfo {

    {
        attributeLength = 2;
    }

    /**
The value of the signature_index item must be a valid index
into the constant_pool table. The constant pool entry at that
index must be a CONSTANT_Utf8_info (§4.5.7) structure
representing either a class signature, if this signature attribute is
an attribute of a ClassFile structure, a method type signature, if
this signature is an attribuute of a method_info structure, or a
field type signature otherwise.
     */
    public short signatureIndex;
}
