package classfile;

import java.util.*;

public class Opcode {

		/**
		*/
    public static final int NOP = 0x00;
		
		/**
Operation
    Push null

    Format
    aconst_null 	

Forms
    aconst_null = 1 (0x1) 

Operand Stack
    ... => ..., null

Description
    Push the null object reference onto the operand stack.

Notes
    The Java virtual machine does not mandate a concrete value for null.
		*/
    public static final int ACONST_NULL = 0x01;
		
		/**
		*/
    public static final int ICONST_M1 = 0x02;
		
		/**
		*/
    public static final int ICONST_0 = 0x03;
		
		/**
		*/
    public static final int ICONST_1 = 0x04;
		
		/**
		*/
    public static final int ICONST_2 = 0x05;
		
		/**
		*/
    public static final int ICONST_3 = 0x06;
		
		/**
		*/
    public static final int ICONST_4 = 0x07;
		
		/**
		*/
    public static final int ICONST_5 = 0x08;
		
		/**
		*/
    public static final int LCONST_0 = 0x09;
		
		/**
		*/
    public static final int LCONST_1 = 0x0a;
		
		/**
		*/
    public static final int FCONST_0 = 0x0b;
		
		/**
		*/
    public static final int FCONST_1 = 0x0c;
		
		/**
		*/
    public static final int FCONST_2 = 0x0d;
		
		/**
		*/
    public static final int DCONST_0 = 0x0e;
		
		/**
		*/
    public static final int DCONST_1 = 0x0f;
		
		/**
		*/
    public static final int BIPUSH = 0x10;
		
		/**
		*/
    public static final int SIPUSH = 0x11;
		
		/**
		*/
    public static final int LDC = 0x12;
		
		/**
		*/
    public static final int LDC_W = 0x13;
		
		/**
		*/
    public static final int LDC2_W = 0x14;
		
		/**
		*/
    public static final int ILOAD = 0x15;
		
		/**
		*/
    public static final int LLOAD = 0x16;
		
		/**
		*/
    public static final int FLOAD = 0x17;
		
		/**
		*/
    public static final int DLOAD = 0x18;
		
		/**
Operation
    Load reference from local variable

    Format
    aload 	
    index 	

Forms
    aload = 25 (0x19)

Operand Stack
    ... => ..., objectref

Description

    The index is an unsigned byte that must be an index into the local variable array of the current frame (��3.6). The local variable at index must contain a reference. The objectref in the local variable at index is pushed onto the operand stack.

Notes

    The aload instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack. This asymmetry with the astore instruction is intentional.

    The aload opcode can be used in conjunction with the wide instruction to access a local variable using a two-byte unsigned index.
		*/
    public static final int ALOAD = 0x19;
		
		/**
		*/
    public static final int ILOAD_0 = 0x1a;
		
		/**
		*/
    public static final int ILOAD_1 = 0x1b;
		
		/**
		*/
    public static final int ILOAD_2 = 0x1c;
		
		/**
		*/
    public static final int ILOAD_3 = 0x1d;
		
		/**
		*/
    public static final int LLOAD_0 = 0x1e;
		
		/**
		*/
    public static final int LLOAD_1 = 0x1f;
		
		/**
		*/
    public static final int LLOAD_2 = 0x20;
		
		/**
		*/
    public static final int LLOAD_3 = 0x21;
		
		/**
		*/
    public static final int FLOAD_0 = 0x22;
		
		/**
		*/
    public static final int FLOAD_1 = 0x23;
		
		/**
		*/
    public static final int FLOAD_2 = 0x24;
		
		/**
		*/
    public static final int FLOAD_3 = 0x25;
		
		/**
		*/
    public static final int DLOAD_0 = 0x26;
		
		/**
		*/
    public static final int DLOAD_1 = 0x27;
		
		/**
		*/
    public static final int DLOAD_2 = 0x28;
		
		/**
		*/
    public static final int DLOAD_3 = 0x29;
		
		/**
Operation
    Load reference from local variable

    Format
    aload_<n> 	

Forms
    aload_0 = 42 (0x2a) aload_1 = 43 (0x2b) aload_2 = 44 (0x2c) aload_3 = 45 (0x2d)

Operand Stack
    ... => ..., objectref

Description
    The <n> must be an index into the local variable array of the current frame (��3.6). The local variable at <n> must contain a reference. The objectref in the local variable at index is pushed onto the operand stack.

Notes
    An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack. This asymmetry with the corresponding astore_<n> instruction is intentional. Each of the aload_<n> instructions is the same as aload with an index of <n>, except that the operand <n> is implicit.
		*/
    public static final int ALOAD_0 = 0x2a;
		
		/**
		*/
    public static final int ALOAD_1 = 0x2b;
		
		/**
		*/
    public static final int ALOAD_2 = 0x2c;
		
		/**
		*/
    public static final int ALOAD_3 = 0x2d;
		
		/**
		*/
    public static final int IALOAD = 0x2e;
		
		/**
		*/
    public static final int LALOAD = 0x2f;
		
		/**
		*/
    public static final int FALOAD = 0x30;
		
		/**
		*/
    public static final int DALOAD = 0x31;
		
		/**
Operation 
Load reference from array 

Format aaload 

Forms 
aaload = 50 (0x32) 

Operand Stack
..., arrayref, index => ..., value 

Description 
The arrayref must be of type reference and must refer to an array whose components are of type reference. The index must be of type int. Both arrayref and index are popped from the operand stack. The reference value in the component of the array at index is retrieved and pushed onto the operand stack. 

Runtime Exceptions 
If arrayref is null, aaload throws a NullPointerException. 

Otherwise, if index is not within the bounds of the array referenced 
by arrayref, the aaload instruction throws an ArrayIndexOutOfBoundsException. 
		*/
    public static final int AALOAD = 0x32;
		
		/**
Operation

    Load byte or boolean from array

    Format

    baload 	

Forms

    baload = 51 (0x33)

Operand Stack

    ..., arrayref, index ..., value

Description

    The arrayref must be of type reference and must refer to an array whose components are of type byte or of type boolean. The index must be of type int. Both arrayref and index are popped from the operand stack. If the components of the array are of type byte, the component of the array at index is retrieved and sign-extended to an int value. If the components of the array are of type boolean, the component of the array at index is retrieved and zero-extended to an int value. In either case the resulting value is pushed onto the operand stack.

Runtime Exceptions

    If arrayref is null, baload throws a NullPointerException. 

    Otherwise, if index is not within the bounds of the array referenced by arrayref, the baload instruction throws an ArrayIndexOutOfBoundsException.

Notes

    The baload instruction is used to load values from both byte and boolean arrays. In Sun's implementation of the Java virtual machine, boolean arrays (arrays of type T_BOOLEAN; see ��3.2 and the description of the newarray instruction in this chapter) are implemented as arrays of 8-bit values. Other implementations may implement packed boolean arrays; the baload instruction of such implementations must be used to access those arrays.
		*/
    public static final int BALOAD = 0x33;
		
		/**
		*/
    public static final int CALOAD = 0x34;
		
		/**
		*/
    public static final int SALOAD = 0x35;
		
		/**
		*/
    public static final int ISTORE = 0x36;
		
		/**
		*/
    public static final int LSTORE = 0x37;
		
		/**
		*/
    public static final int FSTORE = 0x38;
		
		/**
		*/
    public static final int DSTORE = 0x39;
		
		/**
Operation
    Store reference into local variable

    Format
    astore 	
    index 	

Forms
    astore = 58 (0x3a)

Operand Stack
    ..., objectref => ...

Description
    The index is an unsigned byte that must be an index into the local variable array of the current frame (��3.6). The objectref on the top of the operand stack must be of type returnAddress or of type reference. It is popped from the operand stack, and the value of the local variable at index is set to objectref.

Notes
    The astore instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language (see Section 7.13, "Compiling finally"). The aload instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack. This asymmetry with the astore instruction is intentional.

    The astore opcode can be used in conjunction with the wide instruction to access a local variable using a two-byte unsigned index.
		*/
    public static final int ASTORE = 0x3a;
		
		/**
		*/
    public static final int ISTORE_0 = 0x3b;
		
		/**
		*/
    public static final int ISTORE_1 = 0x3c;
		
		/**
		*/
    public static final int ISTORE_2 = 0x3d;
		
		/**
		*/
    public static final int ISTORE_3 = 0x3e;
		
		/**
		*/
    public static final int LSTORE_0 = 0x3f;
		
		/**
		*/
    public static final int LSTORE_1 = 0x40;
		
		/**
		*/
    public static final int LSTORE_2 = 0x41;
		
		/**
		*/
    public static final int LSTORE_3 = 0x42;
		
		/**
		*/
    public static final int FSTORE_0 = 0x43;
		
		/**
		*/
    public static final int FSTORE_1 = 0x44;
		
		/**
		*/
    public static final int FSTORE_2 = 0x45;
		
		/**
		*/
    public static final int FSTORE_3 = 0x46;
		
		/**
		*/
    public static final int DSTORE_0 = 0x47;
		
		/**
		*/
    public static final int DSTORE_1 = 0x48;
		
		/**
		*/
    public static final int DSTORE_2 = 0x49;
		
		/**
		*/
    public static final int DSTORE_3 = 0x4a;
		
		/**
Operation

    Store reference into local variable

    Format

    astore_<n> 	

Forms

    astore_0 = 75 (0x4b) astore_1 = 76 (0x4c) astore_2 = 77 (0x4d) astore_3 = 78 (0x4e)

Operand Stack

    ..., objectref ...

Description

    The <n> must be an index into the local variable array of the current frame (��3.6). The objectref on the top of the operand stack must be of type returnAddress or of type reference. It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.

Notes

    An astore_<n> instruction is used with an objectref of type returnAddress when implementing the finally clauses of the Java programming language (see Section 7.13, "Compiling finally"). An aload_<n> instruction cannot be used to load a value of type returnAddress from a local variable onto the operand stack. This asymmetry with the corresponding astore_<n> instruction is intentional.

    Each of the astore_<n> instructions is the same as astore with an index of <n>, except that the operand <n> is implicit.
		*/
    public static final int ASTORE_0 = 0x4b;
		
		/**
		*/
    public static final int ASTORE_1 = 0x4c;
		
		/**
		*/
    public static final int ASTORE_2 = 0x4d;
		
		/**
		*/
    public static final int ASTORE_3 = 0x4e;
		
		/**
		*/
    public static final int IASTORE = 0x4f;
		
		/**
		*/
    public static final int LASTORE = 0x50;
		
		/**
		*/
    public static final int FASTORE = 0x51;
		
		/**
		*/
    public static final int DASTORE = 0x52;
		
		/**
Forms
    aastore = 83 (0x53)

Operand Stack
    ..., arrayref, index, value => ...

Description
    The arrayref must be of type reference and must refer to an array whose components are of type reference. The index must be of type int and value must be of type reference. The arrayref, index, and value are popped from the operand stack. The reference value is stored as the component of the array at index.

    At run-time, the type of value must be compatible with the type of the components of the array referenced by arrayref. Specifically, assignment of a value of reference type S (source) to an array component of reference type T (target) is allowed only if:

    If S is a class type, then:

        If T is a class type, then S must be the same class (��2.8.1) as T, or S must be a subclass of T;

        If T is an interface type, S must implement (��2.13) interface T.

    If S is an interface type, then:

        If T is a class type, then T must be Object (��2.4.7).

        If T is an interface type, then T must be the same interface as S or a superinterface of S (��2.13.2).

    If S is an array type, namely, the type SC[], that is, an array of components of type SC, then:

        If T is a class type, then T must be Object (��2.4.7).

        If T is an array type TC[], that is, an array of components of type TC, then one of the following must be true:

            TC and SC are the same primitive type (��2.4.1).

            TC and SC are reference types (��2.4.6), and type SC is assignable to TC by these runtime rules.

        If T is an interface type, T must be one of the interfaces implemented by arrays (��2.15). 

Runtime Exceptions

    If arrayref is null, aastore throws a NullPointerException.

    Otherwise, if index is not within the bounds of the array referenced by arrayref, the aastore instruction throws an ArrayIndexOutOfBoundsException.

    Otherwise, if arrayref is not null and the actual type of value is not assignment compatible (��2.6.7) with the actual type of the components of the array, aastore throws an ArrayStoreException. 
		*/
    public static final int AASTORE = 0x53;
		
		/**
		*/
    public static final int BASTORE = 0x54;
		
		/**
		*/
    public static final int CASTORE = 0x55;
		
		/**
		*/
    public static final int SASTORE = 0x56;
		
		/**
		*/
    public static final int POP = 0x57;
		
		/**
		*/
    public static final int POP2 = 0x58;
		
		/**
		*/
    public static final int DUP = 0x59;
		
		/**
		*/
    public static final int DUP_X1 = 0x5a;
		
		/**
		*/
    public static final int DUP_X2 = 0x5b;
		
		/**
		*/
    public static final int DUP2 = 0x5c;
		
		/**
		*/
    public static final int DUP2_X1 = 0x5d;
		
		/**
		*/
    public static final int DUP2_X2 = 0x5e;
		
		/**
		*/
    public static final int SWAP = 0x5f;
		
		/**
		*/
    public static final int IADD = 0x60;
		
		/**
		*/
    public static final int LADD = 0x61;
		
		/**
		*/
    public static final int FADD = 0x62;
		
		/**
		*/
    public static final int DADD = 0x63;
		
		/**
		*/
    public static final int ISUB = 0x64;
		
		/**
		*/
    public static final int LSUB = 0x65;
		
		/**
		*/
    public static final int FSUB = 0x66;
		
		/**
		*/
    public static final int DSUB = 0x67;
		
		/**
		*/
    public static final int IMUL = 0x68;
		
		/**
		*/
    public static final int LMUL = 0x69;
		
		/**
		*/
    public static final int FMUL = 0x6a;
		
		/**
		*/
    public static final int DMUL = 0x6b;
		
		/**
		*/
    public static final int IDIV = 0x6c;
		
		/**
		*/
    public static final int LDIV = 0x6d;
		
		/**
		*/
    public static final int FDIV = 0x6e;
		
		/**
		*/
    public static final int DDIV = 0x6f;
		
		/**
		*/
    public static final int IREM = 0x70;
		
		/**
		*/
    public static final int LREM = 0x71;
		
		/**
		*/
    public static final int FREM = 0x72;
		
		/**
		*/
    public static final int DREM = 0x73;
		
		/**
		*/
    public static final int INEG = 0x74;
		
		/**
		*/
    public static final int LNEG = 0x75;
		
		/**
		*/
    public static final int FNEG = 0x76;
		
		/**
		*/
    public static final int DNEG = 0x77;
		
		/**
		*/
    public static final int ISHL = 0x78;
		
		/**
		*/
    public static final int LSHL = 0x79;
		
		/**
		*/
    public static final int ISHR = 0x7a;
		
		/**
		*/
    public static final int LSHR = 0x7b;
		
		/**
		*/
    public static final int IUSHR = 0x7c;
		
		/**
		*/
    public static final int LUSHR = 0x7d;
		
		/**
		*/
    public static final int IAND = 0x7e;
		
		/**
		*/
    public static final int LAND = 0x7f;
		
		/**
		*/
    public static final int IOR = 0x80;
		
		/**
		*/
    public static final int LOR = 0x81;
		
		/**
		*/
    public static final int IXOR = 0x82;
		
		/**
		*/
    public static final int LXOR = 0x83;
		
		/**
		*/
    public static final int IINC = 0x84;
		
		/**
		*/
    public static final int I2L = 0x85;
		
		/**
		*/
    public static final int I2F = 0x86;
		
		/**
		*/
    public static final int I2D = 0x87;
		
		/**
		*/
    public static final int L2I = 0x88;
		
		/**
		*/
    public static final int L2F = 0x89;
		
		/**
		*/
    public static final int L2D = 0x8a;
		
		/**
		*/
    public static final int F2I = 0x8b;
		
		/**
		*/
    public static final int F2L = 0x8c;
		
		/**
		*/
    public static final int F2D = 0x8d;
		
		/**
		*/
    public static final int D2I = 0x8e;
		
		/**
		*/
    public static final int D2L = 0x8f;
		
		/**
		*/
    public static final int D2F = 0x90;
		
		/**
		*/
    public static final int I2B = 0x91;
		
		/**
		*/
    public static final int I2C = 0x92;
		
		/**
		*/
    public static final int I2S = 0x93;
		
		/**
		*/
    public static final int LCMP = 0x94;
		
		/**
		*/
    public static final int FCMPL = 0x95;
		
		/**
		*/
    public static final int FCMPG = 0x96;
		
		/**
		*/
    public static final int DCMPL = 0x97;
		
		/**
		*/
    public static final int DCMPG = 0x98;
		
		/**
		*/
    public static final int IFEQ = 0x99;
		
		/**
		*/
    public static final int IFNE = 0x9a;
		
		/**
		*/
    public static final int IFLT = 0x9b;
		
		/**
		*/
    public static final int IFGE = 0x9c;
		
		/**
		*/
    public static final int IFGT = 0x9d;
		
		/**
		*/
    public static final int IFLE = 0x9e;
		
		/**
		*/
    public static final int IF_ICMPEQ = 0x9f;
		
		/**
		*/
    public static final int IF_ICMPNE = 0xa0;
		
		/**
		*/
    public static final int IF_ICMPLT = 0xa1;
		
		/**
		*/
    public static final int IF_ICMPGE = 0xa2;
		
		/**
		*/
    public static final int IF_ICMPGT = 0xa3;
		
		/**
		*/
    public static final int IF_ICMPLE = 0xa4;
		
		/**
		*/
    public static final int IF_ACMPEQ = 0xa5;
		
		/**
		*/
    public static final int IF_ACMPNE = 0xa6;
		
		/**
		*/
    public static final int GOTO = 0xa7;
		
		/**
		*/
    public static final int JSR = 0xa8;
		
		/**
		*/
    public static final int RET = 0xa9;
		
		/**
		*/
    public static final int TABLESWITCH = 0xaa;
		
		/**
		*/
    public static final int LOOKUPSWITCH = 0xab;
		
		/**
		*/
    public static final int IRETURN = 0xac;
		
		/**
		*/
    public static final int LRETURN = 0xad;
		
		/**
		*/
    public static final int FRETURN = 0xae;
		
		/**
		*/
    public static final int DRETURN = 0xaf;
		
		/**
Operation
    Return reference from method

    Format
    areturn 	

Forms
    areturn = 176 (0xb0)

Operand Stack
    ..., objectref => [empty]

Description
    The objectref must be of type reference and must refer to an object of a type that is assignment compatible (��2.6.7) with the type represented by the return descriptor (��4.3.3) of the current method. If the current method is a synchronized method, the monitor acquired or reentered on invocation of the method is released or exited (respectively) as if by execution of a monitorexit instruction. If no exception is thrown, objectref is popped from the operand stack of the current frame (��3.6) and pushed onto the operand stack of the frame of the invoker. Any other values on the operand stack of the current method are discarded.

    The interpreter then reinstates the frame of the invoker and returns control to the invoker.

Runtime Exceptions
    If the current method is a synchronized method and the current thread is not the owner of the monitor acquired or reentered on invocation of the method, areturn throws an IllegalMonitorStateException. This can happen, for example, if a synchronized method contains a monitorexit instruction, but no monitorenter instruction, on the object on which the method is synchronized.

    Otherwise, if the virtual machine implementation enforces the rules on structured use of locks described in ��8.13 and if the first of those rules is violated during invocation of the current method, then areturn throws an IllegalMonitorStateException.
		*/
    public static final int ARETURN = 0xb0;
		
		/**
		*/
    public static final int RETURN = 0xb1;
		
		/**
		*/
    public static final int GETSTATIC = 0xb2;
		
		/**
		*/
    public static final int PUTSTATIC = 0xb3;
		
		/**
		*/
    public static final int GETFIELD = 0xb4;
		
		/**
		*/
    public static final int PUTFIELD = 0xb5;
		
		/**
		*/
    public static final int INVOKEVIRTUAL = 0xb6;
		
		/**
		*/
    public static final int INVOKESPECIAL = 0xb7;
		
		/**
		*/
    public static final int INVOKESTATIC = 0xb8;
		
		/**
		*/
    public static final int INVOKEINTERFACE = 0xb9;
		
		/**
		*/
    public static final int XXXUNUSEDXXX1 = 0xba;
		
		/**
		*/
    public static final int NEW = 0xbb;
		
		/**
		*/
    public static final int NEWARRAY = 0xbc;
		
		/**
Operation
    Create new array of reference

    Format
    anewarray 	
    indexbyte1 	
    indexbyte2 	

Forms
    anewarray = 189 (0xbd)

Operand Stack
    ..., count => ..., arrayref

Description
    The count must be of type int. It is popped off the operand stack. The count represents the number of components of the array to be created. The unsigned indexbyte1 and indexbyte2 are used to construct an index into the runtime constant pool of the current class (��3.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The runtime constant pool item at that index must be a symbolic reference to a class, array, or interface type. The named class, array, or interface type is resolved (��5.4.3.1). A new array with components of that type, of length count, is allocated from the garbage-collected heap, and a reference arrayref to this new array object is pushed onto the operand stack. All components of the new array are initialized to null, the default value for reference types (��2.5.1).

Linking Exceptions
    During resolution of the symbolic reference to the class, array, or interface type, any of the exceptions documented in ��5.4.3.1 can be thrown.

Runtime Exception
    Otherwise, if count is less than zero, the anewarray instruction throws a NegativeArraySizeException. 

Notes
    The anewarray instruction is used to create a single dimension of an array of object references or part of a multidimensional array.
		*/
    public static final int ANEWARRAY = 0xbd;
		
		/**
Operation
    Get length of array

    Format
    arraylength 	

Forms
    arraylength = 190 (0xbe)

Operand Stack
    ..., arrayref => ..., length

Description
    The arrayref must be of type reference and must refer to an array. It is popped from the operand stack. The length of the array it references is determined. That length is pushed onto the operand stack as an int.

Runtime Exception
    If the arrayref is null, the arraylength instruction throws a NullPointerException. 
		*/
    public static final int ARRAYLENGTH = 0xbe;
		
		/**
Operation

    Throw exception or error

    Format

    athrow 	

Forms

    athrow = 191 (0xbf)

Operand Stack

    ..., objectref => objectref

Description

    The objectref must be of type reference and must refer to an object that is an instance of class Throwable or of a subclass of Throwable. It is popped from the operand stack. The objectref is then thrown by searching the current method (��3.6) for the first exception handler that matches the class of objectref, as given by the algorithm in ��3.10.

    If an exception handler that matches objectref is found, it contains the location of the code intended to handle this exception. The pc register is reset to that location, the operand stack of the current frame is cleared, objectref is pushed back onto the operand stack, and execution continues. 

    If no matching exception handler is found in the current frame, that frame is popped. If the current frame represents an invocation of a synchronized method, the monitor acquired or reentered on invocation of the method is released or exited (respectively) as if by execution of a monitorexit instruction. Finally, the frame of its invoker is reinstated, if such a frame exists, and the objectref is rethrown. If no such frame exists, the current thread exits.

Runtime Exceptions

    If objectref is null, athrow throws a NullPointerException instead of objectref.

    Otherwise, if the method of the current frame is a synchronized method and the current thread is not the owner of the monitor acquired or reentered on invocation of the method, athrow throws an IllegalMonitorStateException instead of the object previously being thrown. This can happen, for example, if an abruptly completing synchronized method contains a monitorexit instruction, but no monitorenter instruction, on the object on which the method is synchronized.

    Otherwise, if the virtual machine implementation enforces the rules on structured use of locks described in ��8.13 and if the first of those rules is violated during invocation of the current method, then athrow throws an IllegalMonitorStateException instead of the object previously being thrown.

Notes

    The operand stack diagram for the athrow instruction may be misleading: If a handler for this exception is matched in the current method, the athrow instruction discards all the values on the operand stack, then pushes the thrown object onto the operand stack. However, if no handler is matched in the current method and the exception is thrown farther up the method invocation chain, then the operand stack of the method (if any) that handles the exception is cleared and objectref is pushed onto that empty operand stack. All intervening frames from the method that threw the exception up to, but not including, the method that handles the exception are discarded.
		*/
    public static final int ATHROW = 0xbf;
		
		/**
		*/
    public static final int CHECKCAST = 0xc0;
		
		/**
		*/
    public static final int INSTANCEOF = 0xc1;
		
		/**
		*/
    public static final int MONITORENTER = 0xc2;
		
		/**
		*/
    public static final int MONITOREXIT = 0xc3;
		
		/**
		*/
    public static final int WIDE = 0xc4;
		
		/**
		*/
    public static final int MULTIANEWARRAY = 0xc5;
		
		/**
		*/
    public static final int IFNULL = 0xc6;
		
		/**
		*/
    public static final int IFNONNULL = 0xc7;
		
		/**
		*/
    public static final int GOTO_W = 0xc8;
		
		/**
		*/
    public static final int JSR_W = 0xc9;
		
		/**
		*/
    public static final int BREAKPOINT = 0xca;
		
		/**
		*/
    public static final int IMPDEP1 = 0xfe;
		
		/**
		*/
    public static final int IMPDEP2 = 0xff;
		
		/**
		*/
    public static final String[] mnemonics = new String[]{
        "nop",
        "aconst_null",
        "iconst_m1",
        "iconst_0",
        "iconst_1",
        "iconst_2",
        "iconst_3",
        "iconst_4",
        "iconst_5",
        "lconst_0",
        "lconst_1",
        "fconst_0",
        "fconst_1",
        "fconst_2",
        "dconst_0",
        "dconst_1",
        "bipush",
        "sipush",
        "ldc",
        "ldc_w",
        "ldc2_w",
        "iload",
        "lload",
        "fload",
        "dload",
        "aload",
        "iload_0",
        "iload_1",
        "iload_2",
        "iload_3",
        "lload_0",
        "lload_1",
        "lload_2",
        "lload_3",
        "fload_0",
        "fload_1",
        "fload_2",
        "fload_3",
        "dload_0",
        "dload_1",
        "dload_2",
        "dload_3",
        "aload_0",
        "aload_1",
        "aload_2",
        "aload_3",
        "iaload",
        "laload",
        "faload",
        "daload",
        "aaload",
        "baload",
        "caload",
        "saload",
        "istore",
        "lstore",
        "fstore",
        "dstore",
        "astore",
        "istore_0",
        "istore_1",
        "istore_2",
        "istore_3",
        "lstore_0",
        "lstore_1",
        "lstore_2",
        "lstore_3",
        "fstore_0",
        "fstore_1",
        "fstore_2",
        "fstore_3",
        "dstore_0",
        "dstore_1",
        "dstore_2",
        "dstore_3",
        "astore_0",
        "astore_1",
        "astore_2",
        "astore_3",
        "iastore",
        "lastore",
        "fastore",
        "dastore",
        "aastore",
        "bastore",
        "castore",
        "sastore",
        "pop",
        "pop2",
        "dup",
        "dup_x1",
        "dup_x2",
        "dup2",
        "dup2_x1",
        "dup2_x2",
        "swap",
        "iadd",
        "ladd",
        "fadd",
        "dadd",
        "isub",
        "lsub",
        "fsub",
        "dsub",
        "imul",
        "lmul",
        "fmul",
        "dmul",
        "idiv",
        "ldiv",
        "fdiv",
        "ddiv",
        "irem",
        "lrem",
        "frem",
        "drem",
        ".......ineg",
        "lneg",
        "fneg",
        "dneg",
        "ishl",
        "lshl",
        "ishr",
        "lshr",
        "iushr",
        "lushr",
        "iand",
        "land",
        "ior",
        "lor",
        "ixor",
        "lxor",
        "iinc",
        "i2l",
        "i2f",
        "i2d",
        "l2i",
        "l2f",
        "l2d",
        "f2i",
        "f2l",
        "f2d",
        "d2i",
        "d2l",
        "d2f",
        "i2b",
        "i2c",
        "i2s",
        "lcmp",
        "fcmpl",
        "fcmpg",
        "dcmpl",
        "dcmpg",
        "ifeq",
        "ifne",
        "iflt",
        "ifge",
        "ifgt",
        "ifle",
        "if_icmpeq",
        "if_icmpne",
        "if_icmplt",
        "if_icmpge",
        "if_icmpgt",
        "if_icmple",
        "if_acmpeq",
        "if_acmpne",
        "goto",
        "jsr",
        "ret",
        "tableswitch",
        "lookupswitch",
        "ireturn",
        "lreturn",
        "freturn",
        "dreturn",
        "areturn",
        "return",
        "getstatic",
        "putstatic",
        "getfield",
        "putfield",
        "invokevirtual",
        "invokespecial",
        "invokestatic",
        "invokeinterface",
        "xxxunusedxxx1",
        "new",
        "newarray",
        "anewarray",
        "arraylength",
        "athrow",
        "checkcast",
        "instanceof",
        "monitorenter",
        "monitorexit",
        "wide",
        "multianewarray",
        "ifnull",
        "ifnonnull",
        "goto_w",
        "jsr_w",
        "breakpoint",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        "impdep1",
        "impdep2"
    };
		
    private static Map<String, Integer> opcodes;

    public static final String getMnemonic(int opcode) {
        return mnemonics[opcode];
    }

    public static final int getOpcode(String mnemonic) {
        if (opcodes == null) {
            opcodes = new HashMap<String, Integer>();
            for (int i = 0; i <= BREAKPOINT; ++i) {
                opcodes.put(mnemonics[i], i);
            }
            opcodes.put(mnemonics[IMPDEP1], IMPDEP1);
            opcodes.put(mnemonics[IMPDEP2], IMPDEP2);
        }
        return opcodes.get(mnemonic);
    }

    public static final String disassemble(byte[] code) {
        return disassemble(code, 0, code.length);
    }

    public static final String disassemble(byte[] code, int start, int end) {
        if(code!=null) {
            StringBuilder buf = new StringBuilder();

            for(; start<end; ++start) {
                int opcode = 0xff & code[start];
              String mnemonic = mnemonics[opcode];
              buf.append(mnemonic).append("\r\n");

            }
            return buf.toString();
        }
        return null;
        //return "TODO Opcode.disassemble(byte[] code, int start, int end)";
    }

    public static void main(String[] args) {
        System.out.println(getOpcode("nop"));
        System.out.println(getMnemonic(0xfe));


    }
}
