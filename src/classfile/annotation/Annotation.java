package classfile.annotation;
/**
The annotation structure has the following format:
annotation {
u2 type_index;
u2 num_element_value_pairs;
{ u2 element_name_index;
element_value value;
} element_value_pairs[num_element_value_pairs]
}
The items of the annotation structure are as follows:
type_index
 */
public class Annotation {
    /**
The value of the type_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing a field descriptor representing the
annotation type corresponding to the annotation represented by this annotation
structure.
     */
    public short typeIndex;

    /**
The value of the num_element_value_pairs item gives the number of elementvalue
pairs of the annotation represented by this annotation structure. Note that a
maximum of 65535 element-value pairs may be contained in a single annotation.
     */
    public short numElementValuePairs;

    /**
Each value of the element_value_pairs table represents a single element-value
pair in the annotation represented by this annotation structure.
     */
    public ElementValuePair[] elementValuePairs;//[num_element_value_pairs];

    /**
Each
element_value_pairs entry contains the following two items:
     */
    public static class ElementValuePair {
        /**
The value of the element_name_index item must be a valid index into the
constant_pool table. The constant_pool entry at that index must be a
CONSTANT_Utf8_info structure representing the name of the annotation type element
represented by this element_value_pairs entry.
         */
        public short elementNameIndex;

        /**
The value of the value item represents the value of the element-value pair represented
by this element_value_pairs entry.
         */
        public ElementValue value;
    }
}
