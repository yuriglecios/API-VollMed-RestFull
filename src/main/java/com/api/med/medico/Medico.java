package com.api.med.medico;

import com.api.med.endereco.Endereco;
import com.api.med.paciente.DadosAtualizacaoMedicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    Especialidade especialidade;
    @Embedded
    Endereco endereco;
    private boolean ativo;

    public Medico(DadosCadastroMedicoDTO dadosMedico) {
        this.ativo = true;
        this.nome = dadosMedico.nome();
        this.email = dadosMedico.email();
        this.telefone = dadosMedico.telefone();
        this.crm = dadosMedico.crm();
        this.especialidade = dadosMedico.especialidade();
        this.endereco = new Endereco(dadosMedico.endereco());
    }

    public void atualizarMedico(DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        if (dadosAtualizacaoMedicoDTO.nome() != null) {
            this.nome = dadosAtualizacaoMedicoDTO.nome();
        }
        if (dadosAtualizacaoMedicoDTO.telefone() != null) {
            this.telefone = dadosAtualizacaoMedicoDTO.telefone();
        }
        if (dadosAtualizacaoMedicoDTO.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizacaoMedicoDTO.endereco());
        }
    }
}
