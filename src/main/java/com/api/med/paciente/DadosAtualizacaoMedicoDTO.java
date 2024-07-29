package com.api.med.paciente;

import com.api.med.endereco.DadosEnderecoDto;

public record DadosAtualizacaoMedicoDTO(
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDto endereco
) {
}
