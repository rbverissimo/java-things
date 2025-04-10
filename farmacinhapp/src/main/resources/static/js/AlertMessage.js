export class InfoAlert {
    constructor(mensagem){
        this.mensagem = mensagem;
        this.bsType = 'info';
    }
}

export class SuccessAlert {
    constructor(mensagem){
        this.mensagem = mensagem;
        this.bsType = 'success';
    }
}

export class DangerAlert {
    constructor(mensagem){
        this.mensagem = mensagem;
        this.bsType = 'danger';
    }
}