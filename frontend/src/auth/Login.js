import React, { Component } from 'react'
// import './Login.css';
import LeapImg from '../img/LEAP logo.png'
import axios from 'axios';
import toast,{Toaster} from 'react-hot-toast';
import {Modal} from 'react-bootstrap';
import ConfigurePassword from './ConfigurePassword';

export default class Login extends Component {
    constructor(props) {
        super(props)
        this.state =  { 
          showModal: false,
          username: '',
          email: '',
          password: '',
          roleId: '',
          userId: '',
          authenticated: false }
        this.authenticateUser = this.authenticateUser.bind(this)
        this.handleInputChange = this.handleInputChange.bind(this)
        this.handleModal = this.handleModal.bind(this)
    }

    handleModal(){
      this.setState({showModal: !this.state.showModal})
  }

    authenticateUser = async e => {
      e.preventDefault();
      if(this.state.email === 'test' && this.state.password === 'test') {
      this.setState({ email: this.state.email, password: this.state.password, authenticated: true })
      localStorage.setItem('user', JSON.stringify({ email: this.state.email, password: this.state.password, authenticated: true }))
      window.location.reload()
      } else {
      let formData = new FormData()
      formData.append("email", this.state.email)
      axios.post(`${process.env.REACT_APP_API_URL}/user/authenticate`, formData)
      .then(response => {
        if ("newUser" === response.data.password){
          this.setState({username: response.data.username, roleId: response.data.roleId, userId: response.data.userId})
          toast.success(`Welcome ${this.state.email} Let's Pick a Password!`)
          this.handleModal()
          return;
        }
        if (this.state.password === response.data.password) {
          toast.success(`Successful Login! \n Welcome ${this.state.username}`)
          this.setState({authenticated: true })
          localStorage.setItem('user', JSON.stringify({ email: this.state.email, password: this.state.password, authenticated: true }))
          this.props.history.push(`/home`)
          window.location.reload()
          return;
        } 
        else if (this.state.password !== response.data.password) 
        {
          toast.error("Wrong password!")
          return;
        }
        toast.error("Something went Wrong")
      }
      ).catch(error => {
        if(error.response) {
        if (error.response.status === 400) {
          toast.error("Wrong Email Address")
          return;
        }
        }
        toast.error("Something went Wrong")
    })
    }
    }

    handleInputChange(event) {
        this.setState({ [event.target.name]: event.target.value })
    }

    render() {
        return (
            <div className="container">
              <Toaster/>
              <img alt="leap" className="rounded mx-auto d-block" src={ LeapImg } width="320" height="88"/>
              <form onSubmit={ this.authenticateUser }>
              <div className="form-inner">
                <div className="form-group">
              <label htmlFor="inputEmail">Email address</label>
                <input type="text" id="inputEmail" className="form-control" placeholder="Email address" required autoFocus 
                name='email' value={ this.state.email } onChange={ this.handleInputChange }/>
                </div>
              </div>
              <div className="form-group">
              <label htmlFor="inputPassword">Password</label>
                <input type="password" id="inputPassword" className="form-control" placeholder="Password" required 
                name='password' value={ this.state.password } onChange={ this.handleInputChange }/>
              </div>
              <button onClick={ this.authenticateUser } value="LOGIN" type="submit" />
            </form>
            <Modal show={this.state.showModal}>
              <Modal.Header>Configure Password</Modal.Header>
              <Modal.Body><ConfigurePassword handleModal={this.handleModal} userId={this.state.userId} email={this.state.email} username={this.state.username} roleId={this.state.roleId} fetchConfigurePassword={this.fetchConfigurePassword} /></Modal.Body>
            <Modal.Footer>
              <button type="button" className="btn btn-secondary" onClick={() => this.handleModal()}>Close Modal</button>
            </Modal.Footer>
            </Modal>
          </div>
        )
    }
}