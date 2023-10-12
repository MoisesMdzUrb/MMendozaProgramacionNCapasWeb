/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.MMendozaProgramacionNCapasWeb.Controller;

import com.digis01.MMendozaProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.DireccionDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.PaisDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Colonia;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Usuario;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.UsuarioDireccion;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Estado;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Municipio;
import com.digis01.MMendozaProgramacionNCapasWeb.JPA.Rol;
import com.digis01.MMendozaProgramacionNCapasWeb.Util.Util;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
//localhost:8080/usuario/
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioDAOImplementation usuarioDAOImplementation;
    private DireccionDAOImplementation direccionDAOImplementation;
    private RolDAOImplementation rolDAOImplementation;
    private PaisDAOImplementation paisDAOImplementation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @Autowired
    public UsuarioController(UsuarioDAOImplementation usuarioDAOImplementation,
            DireccionDAOImplementation direccionDAOImplementation,
            RolDAOImplementation rolDAOImplementation,
            EstadoDAOImplementation estadoDAOImplementation,
            PaisDAOImplementation paisDAOImplementation,
            MunicipioDAOImplementation municipioDAOImplementation,
            ColoniaDAOImplementation coloniaDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.direccionDAOImplementation = direccionDAOImplementation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.paisDAOImplementation = paisDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;
    }

    //localhost:8080/usuario/listado
    @GetMapping("/listado")
    private String listadoUsuarios(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("");
        usuario.setApellidopaterno("");
        usuario.setApellidomaterno("");
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuario);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariobusqueda", new Usuario());
        return "listadoUsuarios";
    }

    @PostMapping("/listado")
    private String listadoUsuarios(@ModelAttribute("usuariobusqueda") Usuario usuariobusqueda, Model model) {
        List<Usuario> usuarios = usuarioDAOImplementation.GetAll(usuariobusqueda);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuariobusqueda", usuariobusqueda);
        return "listadoUsuarios";
    }

    @GetMapping("/form/{idusuario}")
    public String Form(@PathVariable int idusuario, Model model) {
//        List<Rol> roles = rolDAOImplementation.GetAll();
//        model.addAttribute("roles",roles);
        model.addAttribute("roles", rolDAOImplementation.GetAll());
        model.addAttribute("paises", paisDAOImplementation.GetAll());
        model.addAttribute("estados", estadoDAOImplementation.GetAll());
        model.addAttribute("municipios", municipioDAOImplementation.GetAll());

        if (idusuario == 0) {
            model.addAttribute("usuariodireccion", new UsuarioDireccion());
            return "formUsuarioEditable";
        } else {

            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            usuarioDireccion.setUsuario(usuarioDAOImplementation.GetById(idusuario));
            usuarioDireccion.setDireccion(direccionDAOImplementation.GetByIdUsuario(idusuario));
            model.addAttribute("estados", estadoDAOImplementation.GetByIdPais(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getPais().getIdpais()));
            model.addAttribute("municipios", municipioDAOImplementation.GetByIdEstado(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getIdestado()));
            if (usuarioDireccion.getDireccion().getColonia() != null) {
                model.addAttribute("colonias", coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.getDireccion().getColonia().getMunicipio().getIdmunicipio()));
            }
            
            
            model.addAttribute("usuariodireccion",usuarioDireccion);
            return "formUsuarioEditable";
        }
    }

    @PostMapping("/form")
    public String Update(@Valid @ModelAttribute("usuariodireccion") UsuarioDireccion usuariodireccion,
            BindingResult bindingResult,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model) {
        String imagenBase64 = Util.codificacionImage(imagenFile);
        usuariodireccion.getUsuario().setImagen(imagenBase64);
        if (bindingResult.hasErrors()) {

            model.addAttribute("usuariodireccion", usuariodireccion);
            return "formUsuarioEditable";
        }
        if (usuariodireccion.getUsuario().getIdusuario() > 0) {
            usuarioDAOImplementation.Update(usuariodireccion.getUsuario());
        } else {
            usuariodireccion.getUsuario().setStatus("Y");  //
            int idusuario = usuarioDAOImplementation.Add(usuariodireccion.getUsuario());

            usuariodireccion.getDireccion().setUsuario(new Usuario(idusuario));
 

            direccionDAOImplementation.Add(usuariodireccion.direccion);
        }
        return "redirect:/usuario/listado";
    }

    @GetMapping("/GetEstadoByIdPais")
    @ResponseBody
    public List<Estado> GetEstadoByIdPais(@RequestParam("IdPais") int IdPais) {
        List<Estado> estados = estadoDAOImplementation.GetByIdPais(IdPais);
        return estados;
    }

    @GetMapping("/GetMunicipioByIdEstado")
    @ResponseBody
    public List<Municipio> GetMunicipioByIdEstado(@RequestParam("IdEstado") int IdEstado) {
        List<Municipio> municipios = municipioDAOImplementation.GetByIdEstado(IdEstado);
        return municipios;
    }

    @GetMapping("/GetColoniaByIdMunicipio")
    @ResponseBody
    public List<Colonia> GetColoniaByIdMunicipio(@RequestParam("IdMunicipio") int IdMunicipio) {
        List<Colonia> colonias = coloniaDAOImplementation.GetByIdMunicipio(IdMunicipio);
        return colonias;
    }

    @GetMapping("/ChangeStatus")
    @ResponseBody
    public void ChangeStatus(@RequestParam("idusuario") int idUsuario, @RequestParam("status") boolean status) {
        usuarioDAOImplementation.ChangeStatus(idUsuario, status);
    }

//    @PostMapping("/form")
//    public String Update(@ModelAttribute UsuarioDireccion usuariodireccion) {
//        
//        Rol rol = new Rol();
//        usuariodireccion.getUsuario().setRol(rol);        
//        
//        if (usuariodireccion.getUsuario().getIdusuario() > 0) {
//            usuarioDAOImplementation.Update(usuariodireccion.getUsuario());
//        } else {
//            usuarioDAOImplementation.Add(usuariodireccion.getUsuario());
//            
//            usuariodireccion.setDireccion(usuariodireccion.direccion);
//            
//        }
//        
//        return "redirect:/usuario/listado";
//    }
    @GetMapping("/delete/{idusuario}")
    public String Delete(@PathVariable("idusuario") int idusuario, Model model) {
        usuarioDAOImplementation.Delete(idusuario);
        return "redirect:/usuario/listado";
    }

    //    
//    @PostMapping("addusuario")
//    public String Add(@ModelAttribute Usuario usuario){
//        
//        usuarioDAOImplementation.Add(usuario);
//        
//        return "redirect:/usuario/listado";
//    }
//    
//    @GetMapping("/editarUsuario/{idusuario}")
//    public String Update(@PathVariable int idusuario, Model model){
//        
//        Usuario usuario = usuarioDAOImplementation.GetById(idusuario);
//        model.addAttribute("usuario", usuario);
//        
//        return "formUsuarioEditable";
//    }
}
