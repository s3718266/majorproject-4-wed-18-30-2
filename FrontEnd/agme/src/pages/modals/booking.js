import React, { useState } from "react";
import '../../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

import Modal from "react-modal";

Modal.setAppElement("#root");

export default function Booking() {

    var selectedService = 0;
    const [isOpen, setIsOpen] = useState(false);

    function toggleModal() {
        setIsOpen(!isOpen);
    }

    function renderOptions() {
    
    }

    function handleService(e) {
        selectedService = e.target.value;
    }

    function renderWorkers() {

        let serviceId = selectedService;

    }

    return (
        <div>
            
        </div>
    );
}