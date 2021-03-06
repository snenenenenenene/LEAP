import React, { Component } from "react";
import { Link } from "react-router-dom";
import toast from "react-hot-toast";
import * as sha1 from "js-sha1";
import API from "../../Services/API";
import axios from "axios";
import * as passwordValidator from "password-validator";

export default class Settings extends Component {
  constructor(props) {
    super(props);
    this.state = {
      api: new API(),

      environments: {},
      user: JSON.parse(localStorage.getItem("user")),
      password: "",
    };
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleInputChange = this.handleInputChange.bind(this);
  }

  async componentDidMount() {
    this.state.api.createEntity({ name: "user" });
    this.state.api.createEntity({ name: "role" });
    await this.state.api.endpoints.user
      .getOne({ id: this.state.user.userId })
      .then((response) =>
        this.setState({
          username: response.data.username,
          email: response.data.email,
          roleId: response.data.roleId,
        })
      )
      .catch((error) => {
        toast.error("Could not Load User");
      });
  }

  handleInputChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  handleSubmit = async (e) => {
    e.preventDefault();

    //GENERATE PASSWORD SCHEMA
    var schema = new passwordValidator();
    schema.is().min(8).is().max(50).has().not().spaces();

    var sha = sha1(this.state.password).toUpperCase();
    var prefix = sha.substring(0, 5);
    var suffix = sha.substring(5, sha.length);
    if (schema.validate(this.state.password)) {
      //CHECK IS PASSWORD HAS BEEN BREACHED IN THE PAST USING THE HIBP API
      axios(`https://api.pwnedpasswords.com/range/${prefix}`)
        .then(async (response) => {
          let hashes = response.data.split("\n");
          let breached = false;
          for (var hash of hashes) {
            var h = hash.split(":");
            if (h[0] === suffix) {
              breached = true;
              toast.error("The password has been breached " + h[1] + "times.");
            }
          }
          if (!breached) {
            const formData = new FormData();
            formData.append("userId", this.state.user.userId);
            formData.append("password", this.state.password);
            await this.state.api.endpoints.user
              .changePassword(formData)
              .then(() => {
                toast.success("Password Changed Successfully!");
                this.props.history.push(`/home`);
              })
              .catch((error) => toast.error("Could not Change Password"));
          }
        })
        .catch((error) => {
          toast.error("Something went Wrong...");
        });
    } else {
      toast.error("Password must be at least 8 letters!");
    }
  };

  render() {
    return (
      <div className="container">
        <br></br>
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb">
            <li className="breadcrumb-item">
              <Link to={`/home`}>Home</Link>
            </li>
            <li className="breadcrumb-item">Settings</li>
          </ol>
        </nav>
        <div className="jumbotron">
          <h3>User Settings</h3>
          <form onSubmit={this.handleSubmit}>
            <div className="form-group">
              <label htmlFor="email" className="sr-only">
                Email
              </label>
              <input
                type="text"
                id="email"
                name="email"
                className="form-control-plaintext"
                placeholder="email"
                readonly
                value={this.state.email}
              />
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="username">Username</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  readonly
                  className="form-control-plaintext"
                  placeholder="Username"
                  value={this.state.username}
                  onChange={this.handleInputChange}
                  required
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group col-md-6">
                <label htmlFor="password">Password</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  className="form-control"
                  placeholder="Password"
                  value={this.state.password}
                  onChange={this.handleInputChange}
                  required
                />
              </div>
            </div>
            <button
              className="btn btn-primary"
              type="submit"
              onClick={this.handleSubmit}
            >
              Submit
            </button>
          </form>
        </div>
      </div>
    );
  }
}
