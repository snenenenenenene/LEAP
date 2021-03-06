import React, { Component } from "react";
import LeapImg from "../../img/LEAP_black.png";
import axios from "axios";
import toast from "react-hot-toast";
import "./Login.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";

export default class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showModal: false,
      username: "",
      email: "",
      password: "",
      roleId: "",
      userId: "",
      authenticated: false,
    };
    this.authenticateUser = this.authenticateUser.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleModal = this.handleModal.bind(this);
  }

  //TOGGLE MODAL
  handleModal() {
    this.setState({ showModal: !this.state.showModal });
  }

  //AUTHENTICATE USER AND RECEIVE JWT
  authenticateUser = async (e) => {
    e.preventDefault();
    let formData = new FormData();
    formData.append("email", this.state.email);
    formData.append("password", this.state.password);
    axios
      .post(`${process.env.REACT_APP_API_URL}/user/authenticate`, formData)
      .then((response) => {
        if (response.data === "Bad credentials") {
          return toast.error("Bad Credentials");
        }
        toast.success(`Successful Login! \n Welcome ${this.state.username}`);
        this.setState({ authenticated: true });
        localStorage.setItem(
          "user",
          JSON.stringify({
            email: this.state.email,
            userId: response.data.userId,
            authenticated: true,
            jwt: response.data,
          })
        );
        this.props.history.push(`/home`);
        window.location.reload();
        return;
      })
      .catch((error) => {
        if (error.response) {
          if (error.response.status === 400) {
            toast.error("Wrong Email Address");
            return;
          }
        }
        toast.error("Something went Wrong");
      });
  };

  //HANDLE INPUT CHANGE
  handleInputChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  render() {
    return (
      <div
        className="container"
        style={{
          marginTop: 15 + "%",
          marginBottom: 20 + "%",
        }}
      >
        {/* <Toaster /> */}
        <div className="text-center">
          <img
            alt="leap"
            className="mx-auto d-block logo"
            src={LeapImg}
            width="320"
            height="88"
          />
          <br></br>
          <form onSubmit={this.authenticateUser}>
            <div className="form-group col-sm-5 mx-auto">
              <input
                type="text"
                id="inputEmail"
                placeholder="EMAIL"
                required
                autoFocus
                name="email"
                className="login-input text-center"
                value={this.state.email}
                onChange={this.handleInputChange}
              />
            </div>
            <div className="form-group col-sm-5 mx-auto">
              <input
                size="lg"
                type="password"
                id="inputPassword"
                placeholder="PASSWORD"
                required
                name="password"
                className="login-input text-center"
                value={this.state.password}
                onChange={this.handleInputChange}
              />
            </div>
            <button
              style={{ height: 40 }}
              className="custom-button green"
              onClick={this.authenticateUser}
              type="submit"
            >
              LOGIN
            </button>
            <br></br>
            <br></br>
            <p style={{ fontSize: 10 + "px", color: "grey" }}>
              @Bavo&amp;Stepbros
            </p>
          </form>
          <Link className="bottom-text text-center" to="/forgotpassword">
            <p>Forgot Password</p>
          </Link>
        </div>
      </div>
    );
  }
}
