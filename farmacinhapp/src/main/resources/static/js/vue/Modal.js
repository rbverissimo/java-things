export const ModalComponent = {
    props: {
       id: {
           type: String,
           required: true
       },
       title: {
           type: String,
           default: 'Confirmar ação'
       },
       confirmText: {
           type: String,
           default: 'Confirmar'
       },
       obj: {
           type: Object,
           required: false
       }
    },
    template: `
        <div tabindex="-1":id="id" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 class="modal-title fs-5">{{title}}</h3>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <slot name="body"></slot>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" @click="$emit('confirmed', this.obj)" data-bs-dismiss="modal">{{confirmText}}</button>
                    </div>
                </div>
            </div>
        </div>
    `,
    emits: ['confirmed']
};