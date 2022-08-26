package com.example.demo.events;

import com.example.demo.entities.TransferStatus;

public class CancelTransferStatusEvent {

    private TransferStatus status;

    public CancelTransferStatusEvent( ) {}

    public CancelTransferStatusEvent(TransferStatus status) {
        this.status = status;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }


}
