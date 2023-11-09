import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.opengear.language.http.Java8BaseListener;
import org.opengear.language.http.Java8Lexer;
import org.opengear.language.http.Java8Parser;

import java.util.ArrayList;
import java.util.List;

public class Java8LangDemo {
    public static void main(String[] args) {
        String javaClassContent = "public class SampleClass { void DoSomething(){} }";
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(javaClassContent));

        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();

        ParseTreeWalker walker = new ParseTreeWalker();
        UppercaseMethodListener listener= new UppercaseMethodListener();

        walker.walk(listener, tree);

//        assertThat(listener.getErrors().size(), is(1));
//        assertThat(listener.getErrors().get(0),
//                is("Method DoSomething is uppercased!"));
        System.out.println(listener.errors.size());
        System.out.println(listener.errors.get(0));
    }

    public static class UppercaseMethodListener extends Java8BaseListener {

        private List<String> errors = new ArrayList<>();

        // ... getter for errors

        @Override
        public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
            TerminalNode node = ctx.Identifier();
            String methodName = node.getText();

            if (Character.isUpperCase(methodName.charAt(0))) {
                String error = String.format("Method %s is uppercased!", methodName);
                errors.add(error);
            }
        }
    }
}

