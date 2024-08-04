package com.api.med;

import com.api.med.paciente.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastroPaciente")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    @PostMapping("/paciente")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dadosPaciente, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dadosPaciente);
        pacienteRepository.save(paciente);
        var uri = uriBuilder.path("/cadastroPaciente/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @GetMapping("listarPaciente")
    public ResponseEntity<Page<DadosPacienteRetornoDto>> listarPaciente(Pageable pageable){
        var pacientes = pacienteRepository.findAll(pageable).map(DadosPacienteRetornoDto::new);
        return ResponseEntity.ok(pacientes.map(DadosPacienteRetornoDto::new));
    }

    @PutMapping("/atualizarPaciente")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPacienteDTO dadosAtualizacaoPacienteDTO){
        var paciente = pacienteRepository.getReferenceById(dadosAtualizacaoPacienteDTO.id());
        paciente.atualizarPaciente(dadosAtualizacaoPacienteDTO);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/excluirPaciente/{id}")
    @Transactional
    public ResponseEntity<Paciente> excluirPaciente(@PathVariable Long id){
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativarPaciente/{id}")
    @Transactional
    public ResponseEntity<Paciente> inativarPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.setAtivo(false);
        return ResponseEntity.noContent().build();
    }
}
