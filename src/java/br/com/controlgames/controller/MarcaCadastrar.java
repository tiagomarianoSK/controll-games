/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.dao.GenericDAO;
import br.com.controlgames.dao.MarcaDAO;
import br.com.controlgames.model.Marca;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jeffersonpasserini
 */
@WebServlet(name = "MarcaCadastrar", urlPatterns = {"/MarcaCadastrar"})
public class MarcaCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //Pega sessÃ£o vigente no contexto do servidor
            HttpSession sessao = request.getSession(false);
            //Verifica se existe sessÃ£o e se existe usuario logado
            if (sessao != null) {
                int idMarca = Integer.parseInt(request.getParameter("idmarca"));
                String descricao = request.getParameter("descricao");
                String mensagem = null;

                Marca oMarca = new Marca();
                oMarca.setIdMarca(idMarca);
                oMarca.setDescricao(descricao);
                oMarca.setSituacao("A");

                GenericDAO dao = new MarcaDAO();
                if (dao.cadastrar(oMarca)) {
                    mensagem = "InformaÃ§Ãµes de Marca foram gravadas com Sucesso!";
                } else {
                    mensagem = "Problemas ao gravar informaÃ§Ãµes de Marca. Verifique os dados e tente novamente!";
                }
                
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("MarcaListar").forward(request, response);
            } else {
                request.setAttribute("mensagem", "UsuÃ¡rio nÃ£o Logado ao sistema");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            System.out.println("Problemas no Servelet ao Cadastrar RegiÃ£o! Erro: " + ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
