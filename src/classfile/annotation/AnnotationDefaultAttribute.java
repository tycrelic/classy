package classfile.annotation;

import classfile.AttributeInfo;

/**
4.8.20 The AnnotationDefault attribute
The AnnotationDefault attribute is a variable length attribute in the attributes
table of certain method_info structures, namely those representing elements of
annotation types. The AnnotationDefault attribute records the default value for
the element represented by the method_info structure. Each method_info structures
representing an element of an annotation types may contain at most one AnnotationDefault
attribute. The JVM must make this default value available so it can
be applied by appropriate reflective APIs.
The AnnotationDefault attribute has the following format:
AnnotationDefault_attribute {
u2 attribute_name_index;
u4 attribute_length;
element_value default_value;
}
The items of the AnnotationDefault structure are as follows:
attribute_name_index
The value of the attribute_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the string "AnnotationDefault".
attribute_length
The value of the attribute_length item indicates the length of the attribute,
excluding the initial six bytes. The value of the attribute_length item is thus
dependent on the default value.
 */
public class AnnotationDefaultAttribute extends AttributeInfo {
/**
The default_value item represents the default value of the annotation type element
whose default value is represented by this AnnotationDefault attribute.
 */
    ElementValue defaultValue;
}
