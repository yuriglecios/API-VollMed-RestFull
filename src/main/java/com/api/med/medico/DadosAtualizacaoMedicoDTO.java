package com.api.med.medico;

import com.api.med.endereco.DadosEnderecoDto;

public record DadosAtualizacaoMedicoDTO(
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDto endereco
) {
}
