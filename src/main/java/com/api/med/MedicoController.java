package com.api.med;

import com.api.med.medico.DadosCadastroMedicoDTO;
import com.api.med.medico.DadosRetornoMedicoDto;
import com.api.med.medico.Medico;
import com.api.med.medico.MedicoRepository;
import com.api.med.paciente.DadosAtualizacaoMedicoDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastroMedico")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping("/medico")
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosMedico){
        medicoRepository.save(new Medico(dadosMedico));
    }

    @GetMapping("listarMedicos")
    public Page<DadosRetornoMedicoDto> listarMedicos(Pageable pageable){
        return medicoRepository.findAll(pageable).map(DadosRetornoMedicoDto::new);
    }
    @PutMapping("/atualizarMedico")
    @Transactional
    public void atualizarMedico(@RequestBody @Valid  DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO){
        var medico = medicoRepository.getReferenceById(dadosAtualizacaoMedicoDTO.id());
        medico.atualizarMedico(dadosAtualizacaoMedicoDTO);
    }

    @DeleteMapping("/excluirMedico/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id){
        medicoRepository.deleteById(id);
    }

    @DeleteMapping("/inativarMedico/{id}")
    @Transactional
    public void inativarMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.setAtivo(false);
    }

}
