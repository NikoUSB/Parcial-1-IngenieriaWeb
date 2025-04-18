package com.crud.partial.controllers;

import com.crud.partial.entities.GamesEntity;
import com.crud.partial.services.GamesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v2/games")
@AllArgsConstructor
public class GameController {

    private final GamesService gamesService;

    @PostMapping
    public ResponseEntity createGame(@RequestBody GamesEntity game){
        return new ResponseEntity(gamesService.saveGame(game), HttpStatus.CREATED);
    }

    @GetMapping
    public String main() {
        return """
        <html>
        <head>
            <title>API Games</title>
            <link rel="stylesheet" type="text/css" href="/style.css"
        </head>
        <body>
            <div class="container">
                <h1>Bienvenido a API Games</h1>
                <img src="/main.png" alt="Game Image">
                <br>
                <a href="http://localhost:8080/api/v2/games/">Mostrar JSON con juegos</a>
        </body>
        </html>
    """;
    }

    @GetMapping("/")
    public ResponseEntity getAllGames(){
        return new ResponseEntity(gamesService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getGame(@PathVariable("id") UUID id){
        return new ResponseEntity(gamesService.getGame(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGame(@PathVariable("id") UUID id, @RequestBody GamesEntity game){
        return new ResponseEntity(gamesService.updateGame(id, game), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable("id") UUID id){
        boolean request = gamesService.deleteGame(id);
        if(request){
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
