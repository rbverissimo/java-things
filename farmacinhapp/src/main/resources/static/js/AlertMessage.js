export class Alert {
    constructor(mensagem, type, isFixed = true){
        this.mensagem = mensagem;
        this.type = type;
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

export class SuccessNonFixedAlert extends SuccessAlert {
    constructor(mensagem){
        super(mensagem);
        this.isFixed = false;
    }
}

export class DangerAlert extends Alert {
    constructor(mensagem){
        super(mensagem, 'danger');
    }
}

export class DangerNonFixedAlert extends DangerAlert {
    constructor(mensagem){
        super(mensagem);
        this.isFixed = false;
    }
}