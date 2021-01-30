/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.dao.TipoItemDAO;
import br.com.controlgames.model.TipoItem;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wesle
 */
@WebServlet(name = "TipoItemCadastrar", urlPatterns = {"/TipoItemCadastrar"})
public class TipoItemCadastrar extends HttpServlet {

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
         String descricao = request.getParameter("descricao");
        String mensagem = null;
        
        TipoItem oTipoItem = new TipoItem(0, descricao,"A");
        
        try{
            TipoItemDAO dao = new TipoItemDAO();
            if(dao.cadastrar(oTipoItem)){
                mensagem = "TipoItem cadastrado com Sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar TipoItem. Verifique os dados informados e tente novamente! ";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("TipoItemListar").forward(request, response);
        }catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar amigo! Erro: "+ex.getMessage());
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
