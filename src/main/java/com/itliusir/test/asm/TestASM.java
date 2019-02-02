package com.itliusir.test.asm;

import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import static org.objectweb.asm.Opcodes.*;

/**
 * 字节码校验 case
 *
 * @author liugang
 * @since 2019/1/29
 */

@Slf4j
public class TestASM {

    public static void main(String[] args) throws Exception {

        ClassWriter cw = new ClassWriter(0);
        cw.visit(
                // class format version
                V1_5,
                // class modifiers
                ACC_PUBLIC,
                // class name fully qualified name
                "TestVerification",
                // generic signature
                null,
                // super class fully qualified name
                "java/lang/Object",
                // implemented interfaces
                new String[] { }
        );

        MethodVisitor mv = cw.visitMethod(
                // access modifiers
                ACC_PUBLIC,
                // method name
                "foo",
                // method descriptor
                "()V",
                // generic signature
                null,
                // exceptions
                null
        );
        mv.visitCode();
        mv.visitInsn(FCONST_0);
        mv.visitVarInsn(FSTORE, 1);
        mv.visitVarInsn(ILOAD, 1);
        mv.visitVarInsn(ISTORE, 1);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 2);
        // end method
        mv.visitEnd();

        // end class
        cw.visitEnd();

        byte[] clz = cw.toByteArray();
        FileOutputStream out = new FileOutputStream("TestVerification.class");
        out.write(clz);
        out.close();
    }
}
