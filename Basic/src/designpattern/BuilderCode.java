package designpattern;

import java.util.ArrayList;
import java.util.List;

class Field {
    String type, name;

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

class CodeBuilder {
    String className;
    List<Field> fields = new ArrayList<>();

    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        fields.add(new Field(name, type));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class ")
                .append(className)
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator());
        for (Field field : fields) {
            sb.append("  public ")
                    .append(field.type)
                    .append(" ")
                    .append(field.name)
                    .append(";")
                    .append(System.lineSeparator());
        }
        sb.append("}");

        return sb.toString();
    }
}


public class BuilderCode {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person");
        cb.addField("name", "String")
                .addField("age", "int");
        System.out.println(cb);
    }
}
