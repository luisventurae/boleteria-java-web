package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class DatosCompra_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_form_action;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_submit_value_property_nobody;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_html_form_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_html_submit_value_property_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_form_action.release();
    _jspx_tagPool_html_submit_value_property_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <title>FPF</title>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            #contenedor{\n");
      out.write("\n");
      out.write("                padding: 10px;\n");
      out.write("            }            \n");
      out.write("            #izquierda{\n");
      out.write("                float:left;\n");
      out.write("                padding: 10px;\n");
      out.write("\n");
      out.write("                margin: 40px;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #derecha{\n");
      out.write("                float:left;\n");
      out.write("                padding: 10px;\n");
      out.write("\n");
      out.write("                margin: 40px;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            #publicidad{\n");
      out.write("                float:left;\n");
      out.write("                padding: 10px;\n");
      out.write("\n");
      out.write("                margin: 40px;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("\n");
      out.write("        </style>   \n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <link rel=\"stylesheet\" href=\"main.css\" />\n");
      out.write("    <body>\n");
      out.write("    <center>\n");
      out.write("        <section id=\"banner\">\n");
      out.write("            <div class=\"inner\">\n");
      out.write("                <br/>\n");
      out.write("                <br/>\n");
      out.write("                <br/>\n");
      out.write("                <br/>\n");
      out.write("                <h1>Partido Amistoso</h1>\n");
      out.write("                <p>Peru vs Escocia 8:00 pm</p>\n");
      out.write("\n");
      out.write("            \n");
      out.write("            <div id=\"contenedor\" >\n");
      out.write("\n");
      out.write("                <div id=\"izquierda\">\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <td> <select  class=\"label\"name=\"tribuna\">\n");
      out.write("                                    <option value=\"\">Seleccione tribuna preferida</option>\n");
      out.write("                                    <option value=\"sur\">Sur 99 Soles</option>\n");
      out.write("                                    <option value=\"nor\">Norte 99 Soles</option>\n");
      out.write("                                    <option value=\"sur\">Occidente 499 Soles</option>\n");
      out.write("                                    <option value=\"sur\">Oriente 349 Soles</option>\n");
      out.write("                                </select>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>\n");
      out.write("                                <select  class=\"label\"name=\"tribuna1\">\n");
      out.write("                                    <option value=\"\">Seleccione Cantidad de Entradas</option>\n");
      out.write("                                    <option value=\"1\">1</option>\n");
      out.write("                                    <option value=\"2\">2</option> \n");
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>   \n");
      out.write("                <div id=\"derecha\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            <td>\n");
      out.write("                                <image src=\"Galeria/estadio.jpg\" width=\"350\" height=\"250\"/>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div id=\"publicidad\">\n");
      out.write("                    <table>\n");
      out.write("                        <tr>\n");
      out.write("                            \n");
      out.write("                                ");
      if (_jspx_meth_html_form_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                            \n");
      out.write("                        </tr>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("            <center><video autoplay loop muted playsinline src=\"Galeria/videoplayback.mp4\"></video></center>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <br> \n");
      out.write("            <br/>\n");
      out.write("            <br/>\n");
      out.write("            <br/>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </center>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_html_form_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:form
    org.apache.struts.taglib.html.FormTag _jspx_th_html_form_0 = (org.apache.struts.taglib.html.FormTag) _jspx_tagPool_html_form_action.get(org.apache.struts.taglib.html.FormTag.class);
    _jspx_th_html_form_0.setPageContext(_jspx_page_context);
    _jspx_th_html_form_0.setParent(null);
    _jspx_th_html_form_0.setAction("/Pago");
    int _jspx_eval_html_form_0 = _jspx_th_html_form_0.doStartTag();
    if (_jspx_eval_html_form_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("                                ");
        if (_jspx_meth_html_submit_0((javax.servlet.jsp.tagext.JspTag) _jspx_th_html_form_0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("                                ");
        int evalDoAfterBody = _jspx_th_html_form_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_form_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_form_action.reuse(_jspx_th_html_form_0);
      return true;
    }
    _jspx_tagPool_html_form_action.reuse(_jspx_th_html_form_0);
    return false;
  }

  private boolean _jspx_meth_html_submit_0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_form_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:submit
    org.apache.struts.taglib.html.SubmitTag _jspx_th_html_submit_0 = (org.apache.struts.taglib.html.SubmitTag) _jspx_tagPool_html_submit_value_property_nobody.get(org.apache.struts.taglib.html.SubmitTag.class);
    _jspx_th_html_submit_0.setPageContext(_jspx_page_context);
    _jspx_th_html_submit_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_form_0);
    _jspx_th_html_submit_0.setValue("CONTINUAR");
    _jspx_th_html_submit_0.setProperty("pag");
    int _jspx_eval_html_submit_0 = _jspx_th_html_submit_0.doStartTag();
    if (_jspx_th_html_submit_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_submit_value_property_nobody.reuse(_jspx_th_html_submit_0);
      return true;
    }
    _jspx_tagPool_html_submit_value_property_nobody.reuse(_jspx_th_html_submit_0);
    return false;
  }
}
