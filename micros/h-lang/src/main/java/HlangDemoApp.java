import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.opengear.language.http.HBaseListener;
import org.opengear.language.http.HLexer;
import org.opengear.language.http.HParser;

import java.io.IOException;
import java.nio.file.Path;

public class HlangDemoApp {
    public static void main(String[] args) throws IOException {
        // token stream
        Path fileName = Path.of(System.getProperty("user.dir") + "/micros/h-lang/src/main/resources/http_request.hr");
        Path absolutePath = fileName.toAbsolutePath();
        System.out.println(absolutePath.toString());
        CharStream input = CharStreams.fromPath(fileName);
        HLexer lexer = new HLexer(input);
        HParser parser = new HParser(new BufferedTokenStream(lexer));
        ParseTreeWalker walker = new ParseTreeWalker();
        HBaseListener listener = new XLis();
        walker.walk(listener, parser.getContext());
//        HParser.Http_requestContext httpRequestContext = parser.http_request();
    }

    public static class XLis extends HBaseListener {
        @Override
        public void enterCode(HParser.CodeContext ctx) {
            super.enterCode(ctx);
            System.out.println("----");
        }
    }
}
