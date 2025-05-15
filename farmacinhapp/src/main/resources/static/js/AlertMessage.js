export class Alert {
    constructor(mensagem, bsType, isFixed = true){
        this.mensagem = mensagem;
        this.bsType = bsType;
        this.isFixed = isFixed;
    }
}

export class InfoAlert extends Alert {
    constructor(mensagem){
        super(mensagem, 'info');
    }
}

export class SuccessAlert extends Alert {
    constructor(mensagem){
        super(mensagem, 'success');
    }
}

export class DangerAlert extends Alert {
    constructor(mensagem){
        super(mensagem, 'danger');
    }
}