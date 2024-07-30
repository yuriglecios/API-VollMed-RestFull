package com.api.med.medico;

import com.api.med.endereco.DadosEnderecoDto;
import com.api.med.endereco.Endereco;

public record DadosDetalheMedicoDto(
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco
) {

    public DadosDetalheMedicoDto(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
