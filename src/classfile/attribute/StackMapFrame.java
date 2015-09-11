package classfile.attribute;
/**
The stack_map_frame structure consists of a one-byte tag followed by zero or
more bytes, giving more information, depending upon the tag.
A stack map frame may belong to one of several frame types
union stack_map_frame {
same_frame;
same_locals_1_stack_item_frame;
same_locals_1_stack_item_frame_extended;
chop_frame;
same_frame_extended;
append_frame;
full_frame;
}
The frame type same_frame is represented by tags in the range [0-63]. If the
frame type is same_frame, it means the frame has exactly the same locals as the
previous stack map frame and that the number of stack items is zero. The
offset_delta value for the frame is the value of the tag item, frame_type. The
form of such a frame is then:
same_frame {
u1 frame_type = SAME;/* 0-63 * /
}
The frame type same_locals_1_stack_item_frame is represented by tags in
the range [64, 127]. If the frame_type is same_locals_1_stack_item_frame, it
means the frame has exactly the same locals as the previous stack map frame and
that the number of stack items is 1. The offset_delta value for the frame is the
value (frame_type - 64). There is a verification_type_info following the
frame_type for the one stack item. The form of such a frame is then:
same_locals_1_stack_item_frame {
u1 frame_type = SAME_LOCALS_1_STACK_ITEM;/* 64-127 * /
verification_type_info stack[1];
}
Tags in the range [128-246] are reserved for future use.
The frame type same_locals_1_stack_item_frame_extended is represented
by the tag 247. The frame type same_locals_1_stack_item_frame_extended
indicates that the frame has exactly the same locals as the previous stack map
frame and that the number of stack items is 1. The offset_delta value for the
frame is given explicitly. There is a verification_type_info following the
frame_type for the one stack item. The form of such a frame is then:
same_locals_1_stack_item_frame_extended {
u1 frame_type = SAME_LOCALS_1_STACK_ITEM_EXTENDED;/*
247 * /
u2 offset_delta;
verification_type_info stack[1];
}
The frame type chop_frame is represented by tags in the range [248-250]. If the
frame_type is chop_frame, it means that the operand stack is empty and the
current locals are the same as the locals in the previous frame, except that the k
last locals are absent. The value of k is given by the formula 251-frame_type.
The form of such a frame is then:
chop_frame {
u1 frame_type=CHOP; /* 248-250 * /
u2 offset_delta;
}
The frame type same_frame_extended is represented by the tag value 251. If the
frame type is same_frame_extended, it means the frame has exactly the same
locals as the previous stack map frame and that the number of stack items is zero.
The form of such a frame is then:
same_frame_extended {
u1 frame_type = SAME_FRAME_EXTENDED;/* 251* /
u2 offset_delta;
}
The frame type append_frame is represented by tags in the range [252-254]. If
the frame_type is append_frame, it means that the operand stack is empty and the
current locals are the same as the locals in the previous frame, except that k
additional locals are defined. The value of k is given by the formula frame_type-
251.
The form of such a frame is then:
append_frame {
u1 frame_type = APPEND; /* 252-254 * /
u2 offset_delta;
verification_type_info locals[frame_type -251];
}
The 0th entry in locals represents the type of the first additional local variable. If
locals[M] represents local variable N, then locals[M+1] represents local
variable N+1 if locals[M] is one of Top_variable_info,
Integer_variable_info, Float_variable_info, Null_variable_info,
UninitializedThis_variable_info, Object_variable_info, or
Uninitialized_variable_info, otherwise locals[M+1] represents local
variable N+2. It is an error if, for any index i, locals[i] represents a local
variable whose index is greater than the maximum number of local variables for
the method.
The frame type full_frame is represented by the tag value 255. The form of such
a frame is then:
full_frame {
u1 frame_type = FULL_FRAME; /* 255 * /
u2 offset_delta;
u2 number_of_locals;
verification_type_info locals[number_of_locals];
u2 number_of_stack_items;
verification_type_info stack[number_of_stack_items];
}
The 0th entry in locals represents the type of local variable 0. If locals[M]
represents local variable N, then locals[M+1] represents local variable N+1 if
locals[M] is one of Top_variable_info, Integer_variable_info,
Float_variable_info, Null_variable_info,
UninitializedThis_variable_info, Object_variable_info, or
Uninitialized_variable_info, otherwise locals[M+1] represents local
variable N+2. It is an error if, for any index i, locals[i] represents a local
variable whose index is greater than the maximum number of local variables for
the method.
The 0th entry in stack represents the type of the bottom of the stack, and
subsequent entries represent types of stack elements closer to the top of the
operand stack. We shall refer to the bottom element of the stack as stack element
0, and to subsequent elements as stack element 1, 2 etc. If stack[M] represents
stack element N, then stack[M+1] represents stack element N+1 if stack[M] is
one of Top_variable_info, Integer_variable_info,
Float_variable_info, Null_variable_info,
UninitializedThis_variable_info, Object_variable_info, or
Uninitialized_variable_info, otherwise stack[M+1] represents stack
element N+2. It is an error if, for any index i, stack[i] represents a stack entry
whose index is greater than the maximum operand stack size for the method.
We say that an instruction in the bytecode has a corresponding stack map frame if
the offset in the offset item of the stack map frame is the same as the offset of the
instruction in the bytecodes.
 */
public class StackMapFrame {
    public byte frameType;
    int offsetDelta;
    int numberOfLocals;
    VerificationTypeInfo[] locals;
    int numberOfStackItems;
    VerificationTypeInfo[] stack;
}
