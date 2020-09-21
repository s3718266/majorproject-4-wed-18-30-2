import React, { useState } from "react";
import '../../App.css';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

import Modal from "react-modal";

Modal.setAppElement("#root");

export default function Booking() {
    const [isOpen, setIsOpen] = useState(false);

    function toggleModal() {
        setIsOpen(!isOpen);
    }

    return (
        <div className="App">
            <Button onClick={toggleModal}>Book a service</Button>

            <Modal
                isOpen={isOpen}
                onRequestClose={toggleModal}
                contentLabel="My dialog"
            >
                <Form className="login-form">
                    <h1 className="font-weight-bold" id="heading">Booking</h1>
                    <div className="alert alert-danger d-none" id="errorMessage">
                    </div>

                    <FormGroup>
                        <Label>Date</Label>
                        <Input type="date" name="email" placeholder="Email"></Input>
                    </FormGroup>

                    <FormGroup>
                        <Label for="exampleSelect">Services</Label>
                        <Input type="select" name="select" id="exampleSelect">
                            <option value="Hairdresser">Hairdresser</option>
                            <option value="Lawn Mowner">Lawn Mowner</option>
                            <option value="Dog Walker">Dog Walker</option>
                        </Input>
                    </FormGroup>
                    <Button className="btn-lg btn-success btn-block mt-5 mb-3" type="submit">
                        Book
                    </Button>



                </Form>
                <Button onClick={toggleModal}>
                    Close modal
                </Button>
            </Modal>
        </div>
    );
}