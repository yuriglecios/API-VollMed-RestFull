package com.api.med.paciente;

import com.api.med.endereco.DadosEnderecoDto;

public record DadosAtualizacaoPacienteDTO(
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDto endereco
) {
}
