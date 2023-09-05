package gg.moonflower.molangcompiler.core.ast;

import gg.moonflower.molangcompiler.api.exception.MolangException;
import gg.moonflower.molangcompiler.core.compiler.MolangBytecodeEnvironment;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;
import ru.timeconqueror.molang.custom.Aliases;
import ru.timeconqueror.molang.custom.QueryDomain;

import java.util.Locale;
import java.util.Objects;

/**
 * Retrieves the value of a variable and puts it onto the stack.
 *
 * @param object The object the variable is stored in
 * @param name   The name of the variable
 * @author Ocelot
 */
public record VariableGetNode(String object, String name) implements Node {
    public VariableGetNode {
        name = name.toLowerCase(Locale.ROOT);

        object = Aliases.resolve(object);
        if (Objects.equals(object, "query")) {
            String domain = QueryDomain.getDomain(name);
            if (domain != null) {
                object = domain;
            }
        }
    }

    @Override
    public String toString() {
        return this.object + "." + this.name;
    }

    @Override
    public boolean isConstant() {
        return false;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public void writeBytecode(MethodNode method, MolangBytecodeEnvironment environment, @Nullable Label breakLabel, @Nullable Label continueLabel) throws MolangException {
        int index = environment.loadVariable(method, this.object, this.name);
        method.visitVarInsn(Opcodes.FLOAD, index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (VariableGetNode) obj;
        return Objects.equals(this.object, that.object) &&
                Objects.equals(this.name, that.name);
    }

}