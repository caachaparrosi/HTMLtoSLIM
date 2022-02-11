package com.example.traductorfinal.services;

import com.example.traductorfinal.grammar.HTMLParser;
import com.example.traductorfinal.grammar.HTMLParserBaseListener;

import java.util.Locale;

public class Listener extends HTMLParserBaseListener {

    public static String cadena = "";// contiene toda la traduccion que se muestra por consola ypor el archivo salida.txt
    int indent = -1;

    public String escribir(String token){//captura toda la traduccion en una cadena
        cadena = cadena+token;
        return  cadena;
    }

    public void limpiar(){
        cadena = "";
    }

    private String indentation(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            s.append("\t");
        }
        return s.toString();
    }

    @Override
    public void enterHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        super.enterHtmlDocument(ctx);
        if(ctx.DTD() != null) {
            cadena += ctx.DTD().toString().toLowerCase(Locale.ROOT).replace("<!", " ").replace(">"," ").replaceAll("\\s+","") ;
        }
    }

    @Override
    public void enterScriptletOrSeaWs(HTMLParser.ScriptletOrSeaWsContext ctx) {
        super.enterScriptletOrSeaWs(ctx);
        cadena += ctx.SEA_WS();
    }

    @Override
    public void enterHtmlElement(HTMLParser.HtmlElementContext ctx){
        super.enterHtmlElement(ctx);
        indent++;
        if(ctx.script() != null || ctx.style() != null){
        }else{
            String tag = ctx.TAG_NAME().get(0).toString();
            cadena += tag + " ";
        }

    }

    @Override
    public void enterHtmlMisc(HTMLParser.HtmlMiscContext ctx) {
        super.enterHtmlMisc(ctx);
        cadena += ctx.SEA_WS();
    }

    @Override
    public void enterHtmlComment(HTMLParser.HtmlCommentContext ctx) {
        super.enterHtmlComment(ctx);
        cadena += ctx.HTML_COMMENT().toString().replace("<!--", "/! ").replace("-->"," " + "\n");
    }

    @Override
    public void exitHtmlElement(HTMLParser.HtmlElementContext ctx) {
        super.exitHtmlElement(ctx);
        if(ctx.TAG_SLASH_CLOSE() != null) {
            cadena += "/" + " ";
        }
        indent--;
    }

    @Override
    public void enterHtmlChardata(HTMLParser.HtmlChardataContext ctx) {
        super.enterHtmlChardata(ctx);

        if(ctx.HTML_TEXT() != null) {
            cadena += ctx.HTML_TEXT();
        }else if(ctx.SEA_WS() != null){
            cadena += ctx.SEA_WS();
        }
    }

    @Override
    public void enterHtmlAttribute(HTMLParser.HtmlAttributeContext ctx) {
        super.enterHtmlAttribute(ctx);
        if(ctx.TAG_NAME().toString().equals("id")) {
            cadena += "#" + ctx.ATTVALUE_VALUE().toString().replaceAll("\"", " ").replaceAll("\\s+","") + " ";
        }else if(ctx.TAG_NAME().toString().equals("class")){
            cadena += "." + ctx.ATTVALUE_VALUE().toString().replaceAll("\"", " ").replaceAll("\\s+","") + " ";
        }else if(ctx.TAG_NAME().toString().equals("title")){
            cadena += ctx.TAG_NAME().toString() + ctx.TAG_EQUALS().toString() + "(" + ctx.ATTVALUE_VALUE().toString() + ")"+ " ";
        }else if(ctx.TAG_NAME().toString().equals("alt")) {
            cadena += ctx.TAG_NAME().toString() + ctx.TAG_EQUALS().toString() + "(" + ctx.ATTVALUE_VALUE().toString() + ")" + " ";
        }else{
            cadena += ctx.TAG_NAME().toString() + ctx.TAG_EQUALS().toString() + ctx.ATTVALUE_VALUE().toString() + " ";
        }
    }

    @Override
    public void enterScript(HTMLParser.ScriptContext ctx) {
        super.enterScript(ctx);
        if(ctx.SCRIPT_OPEN().toString().equals("<script>")){
            cadena += "javascript:";
        }else if(ctx.SCRIPT_OPEN() != null){
            cadena += ctx.SCRIPT_OPEN().toString().replaceAll("<script", "script").replace(">"," ");
        }
        cadena += ctx.SCRIPT_BODY().toString().replace("</script>", " ").replaceAll("\n    ","\n     |").replaceAll("\n","\n |");
    }

    @Override
    public void enterStyle(HTMLParser.StyleContext ctx) {
        super.enterStyle(ctx);
        if(ctx.STYLE_OPEN() != null){
            cadena += "css:";
        }
        cadena += ctx.STYLE_BODY().toString().replace("</style>", " ").replaceAll("\n","\n |").replaceAll("\n\n","");

    }

    @Override
    public void exitHtmlDocument(HTMLParser.HtmlDocumentContext ctx) {
        super.exitHtmlDocument(ctx);
    }
}