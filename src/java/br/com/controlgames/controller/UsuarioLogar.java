/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

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
@WebServlet(name = "UsuarioLogar", urlPatterns = {"/UsuarioLogar"})
public class UsuarioLogar extends HttpServlet {

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

        String loginUsuario = request.getParameter("loginuser");
        String senhaUsuario = request.getParameter("senhauser");
        String usuarioLogado = "";

        try {
            UsuarioDAO oUsuarioDAO = new UsuarioDAO();
            Usuario oUsuario = oUsuarioDAO.logar(loginUsuario, senhaUsuario);

            if (oUsuario != null) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("idusuario", oUsuario.getIdUsuario());
                sessao.setAttribute("nomeusuario", oUsuario.getNome());
                usuarioLogado = "ok";
            } else {
                System.out.println("Usuario ou senha invalidas, verificar dados");
                usuarioLogado = "";
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(usuarioLogado);
        }catch (Exception ex){
            System.out.println("Problemas ao logar Usuario! Erro"+ex.getMessage());
            ex.printStackTrace();
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
