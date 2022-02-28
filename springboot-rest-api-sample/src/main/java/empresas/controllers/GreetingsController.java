package empresas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import empresas.model.Empresa;
import empresas.repository.EmpresaRepository;


@RestController
public class GreetingsController {
	
	@Autowired /*Injecao de dependencia - CDI*/
	private EmpresaRepository empresaRepository;
   
    @RequestMapping(value = "empresas/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    
    @GetMapping(value = "listaEmpresas")/*método de API*/
    @ResponseBody /*retorna os dados para o corpo da reposta*/
    public ResponseEntity<List<Empresa>> listaEmpresas(){
    	
    	List<Empresa> empresas = empresaRepository.findAll();/*executa a consulta no banco de dados*/
    	
    	return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);/*retorna a lista em JSON*/
    	
    }
    
    @DeleteMapping(value = "deletar")
    @ResponseBody 
    public ResponseEntity<String> deletar(@RequestParam Long id){
    	
        empresaRepository.deleteById(id);
    	  	
		return new ResponseEntity<String>("Empresa deletada com sucesso ",HttpStatus.OK);
    	
    }
    
    
    @PostMapping(value = "salvar")/*mapeia a URL*/
    @ResponseBody /*Descrição da requisição*/
    public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa){/*recebe os dados para salvar*/
    	
     Empresa emp = 	empresaRepository.save(empresa);
    	  	
		return new ResponseEntity<Empresa>(HttpStatus.CREATED);
    	
    }
    
    @GetMapping(value = "buscar")
    @ResponseBody 
    public ResponseEntity<Empresa> buscar(@RequestParam(name = "id") Long id){
    	
     Empresa empresa =  empresaRepository.findById(id).get();
    	  	
		return new ResponseEntity<Empresa>(empresa,HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarPorNome")
    @ResponseBody 
    public ResponseEntity<List<Empresa>> buscarPorNome(@RequestParam (name = "nome")String nome){
    	
    	List<Empresa> empresas = empresaRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
    	
    }
    
}





