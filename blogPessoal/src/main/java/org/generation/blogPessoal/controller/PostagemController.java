package org.generation.blogPessoal.controller;

import org.generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.generation.blogPessoal.model.Postagem;

import java.util.List;


@RestController
@RequestMapping ("/postagens")
@CrossOrigin ("*")
public class PostagemController 
{
	private static final Object Resp = null;
	@Autowired
	private PostagemRepository repository;
	
	
	@GetMapping
	public ResponseEntity <List<Postagem>>GetAll()
		{
		return ResponseEntity.ok(repository.findAll());
		}
	
	@GetMapping ("/{Id}")
	public ResponseEntity<Object> GetbyId (@PathVariable Long id)
		{
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(Resp))
				.orElse(ResponseEntity.notFound().build());
		}

	@GetMapping ("/titulo/{Titulo}")
	public ResponseEntity<List<Postagem>> GetbyTitulo (@PathVariable String titulo)
		{
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		}
	
    @PostMapping
    public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
    	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    	}
    @PutMapping
    public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
    	return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    	}
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id) 
    	{
    	repository.deleteById(id);
	
    	}
    
}