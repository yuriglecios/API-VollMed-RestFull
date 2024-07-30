package com.api.med;

import com.api.med.medico.*;
import com.api.med.paciente.DadosAtualizacaoMedicoDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cadastroMedico")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping("/medico")
    @Transactional
    public ResponseEntity<DadosDetalheMedicoDto> cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosMedico, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dadosMedico);
        medicoRepository.save(medico);
        var uri = uriBuilder.path("/cadastroMedico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalheMedicoDto(medico));
    }

    @GetMapping("listarMedicos")
    public ResponseEntity<Page<DadosRetornoMedicoDto>>  listarMedicos(Pageable pageable){
        var page =  medicoRepository.findAll(pageable).map(DadosRetornoMedicoDto::new);
        return ResponseEntity.ok(page);
    }
    @PutMapping("/atualizarMedico")
    @Transactional
    public ResponseEntity<DadosDetalheMedicoDto> atualizarMedico(@RequestBody @Valid  DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO){
        var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedicoDTO.id());
        medico.atualizarMedico(dadosAtualizacaoMedicoDTO);
        return ResponseEntity.ok(new DadosDetalheMedicoDto(medico));
    }

    @DeleteMapping("/excluirMedico/{id}")
    @Transactional
    public ResponseEntity<Medico> excluirMedico(@PathVariable Long id){
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/inativarMedico/{id}")
    @Transactional
    public ResponseEntity<Medico> inativarMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
        return ResponseEntity.noContent().build();
    }

}
