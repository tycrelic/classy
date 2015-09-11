package classfile.annotation;

/**
4.8.16.1 The element_value structure
The element_value structure is a discriminated union representing the value of an
element-value pair. It is used to represent element values in all attributes that
describe annotations ( RuntimeVisibleAnnotations, RuntimeInvisibleAnnotations,
RuntimeVisibleParameterAnnotations, and RuntimeInvisibleParameterAnnotations).
The element_value structure has the following format:
element_value {
u1 tag;
union {
u2 const_value_index;
{
u2 type_name_index;
u2 const_name_index;
} enum_const_value;
u2 class_info_index;
annotation annotation_value;
{
u2 num_values;
element_value values[num_values];
} array_value;
} value;
}
 */
public abstract class ElementValue {
    /**
The tag item indicates the type of this annotation element-value pair. The letters
'B', 'C', 'D', 'F', 'I', 'J', 'S', and 'Z' indicate a primitive type. These letters are interpreted
as BaseType characters (§Table 4.2). The other legal values for tag are listed
with their interpretations in this table:
Table 4.8
tag value Element Type
s String
e enum constant
c class
@ annotation type
[ array

value
The value item represents the value of this annotation element. This item is a
union. The tag item, above, determines which item of the union is to be used:
     */
    byte tag;

}
