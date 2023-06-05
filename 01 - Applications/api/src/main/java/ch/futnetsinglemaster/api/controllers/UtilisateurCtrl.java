package ch.futnetsinglemaster.api.controllers;


import ch.futnetsinglemaster.api.beans.DeleteIdRequest;
import ch.futnetsinglemaster.api.beans.ResultJSON;
import ch.futnetsinglemaster.api.dto.PostUserDTO;
import ch.futnetsinglemaster.api.dto.PutUserDTO;
import ch.futnetsinglemaster.api.dto.UtilisateurDTO;
import ch.futnetsinglemaster.api.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/user")
public class UtilisateurCtrl {

    @Autowired
    private UtilisateurService userService;

    // =====================
    //         GET
    // =====================
    @GetMapping(path = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> getUsers(HttpServletRequest request){
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 5)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            return new ResponseEntity<>(new ResultJSON(200, "success", "", userService.getUsers()), new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // =====================
    //         POST
    // =====================
    @PostMapping(path = "/saveUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> postUtilisateur(@RequestBody PostUserDTO user, HttpServletRequest request) {
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 10)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res =userService.saveUser(user);
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.CREATED);

            }
            return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // =====================
    //         PUT
    // =====================
    @PutMapping(path = "/putUser",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> putUtilisateur(@RequestBody PutUserDTO user,  HttpServletRequest request) {
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null){
            if(sessionUser.getNiveau() < 10)
                return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res = userService.putUser(user);
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(new ResultJSON(200, "success", "", res.getResponseObject()), new HttpHeaders(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);

            }

        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    // =====================
    //         DELETE
    // =====================
    @DeleteMapping(path = "/deleteUserById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultJSON> deleteUtilisateur(@RequestBody DeleteIdRequest userId, HttpServletRequest request){
        UtilisateurDTO sessionUser = (UtilisateurDTO) request.getSession().getAttribute("user");
        if(sessionUser != null ){
            if(sessionUser.getNiveau() < 10)
            return new ResponseEntity<>(new ResultJSON(403, "Forbiden", "Vous n'avez pas les droits nécessaire pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.FORBIDDEN);
            ResultJSON res = userService.deleteUser(userId.id());
            if(res.getResponseCode() == 200){
                return new ResponseEntity<>(new ResultJSON(200, "success", "", res.getResponseObject()), new HttpHeaders(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResultJSON(401, "Unauthorized", "Vous n'avez pas les droits pour acceder à ces ressources", null), new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
