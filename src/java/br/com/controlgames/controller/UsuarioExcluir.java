/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.dao.GenericDAO;
import br.com.controlgames.dao.UsuarioDAO;
import br.com.controlgames.model.Usuario;
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
 * @author wesle
 */
@WebServlet(name = "UsuarioExcluir", urlPatterns = {"/UsuarioExcluir"})
public class UsuarioExcluir extends HttpServlet {

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
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        String mensagem = null;

        try {

            HttpSession sessao = request.getSession(false);

            if (sessao != null) {

                UsuarioDAO oUsuarioDAO = new UsuarioDAO();
                Usuario oUsuario = (Usuario) oUsuarioDAO.carregar(idUsuario);

                GenericDAO dao = new UsuarioDAO();
                if (dao.excluir(oUsuario)) {
                    mensagem = "Usuário excluido com Sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar Usuário. Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("UsuarioListar").forward(request, response);
            }else{
                request.setAttribute("mensagem", "usuario nao logado no sistema");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            }catch(Exception ex){
            System.out.println("Problemas na Servlet ao excluir Usuário! Erro: "+ex.getMessage());
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
