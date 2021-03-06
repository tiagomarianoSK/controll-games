/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.dao.PlataformaDAO;
import br.com.controlgames.model.Plataforma;
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
@WebServlet(name = "PlataformaCadastrar", urlPatterns = {"/PlataformaCadastrar"})
public class PlataformaCadastrar extends HttpServlet {

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
        
        Plataforma oPlataforma = new Plataforma(0, descricao,"A");
        
        try{
            PlataformaDAO dao = new PlataformaDAO();
            if(dao.cadastrar(oPlataforma)){
                mensagem = "Plataforma cadastrado com Sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Regiao. Verifique os dados informados e tente novamente! ";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("PlataformaListar").forward(request, response);
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
