package classfile.annotation;

/**
The array_value item is used if the tag item is '['. The array_value item consists
of the following two items:
 */
public abstract class ArrayValue extends ElementValue {
    /**
The value of the num_values item gives the number of elements in the arraytyped
value represented by this element_value structure. Note that a maximum of
65535 elements are permitted in an array-typed element value.
     */
    short numValues;
    
    /**
Each value of the values table gives the value of an element of the array-typed
value represented by this element_value structure.
     */
     ElementValue values;//[num_values];
}
