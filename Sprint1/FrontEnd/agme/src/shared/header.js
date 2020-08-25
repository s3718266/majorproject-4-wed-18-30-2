import React from "react";

function Header() {
    return (
        <div className="header">
            <nav className="navbar sticky-top navbar-dark bg-secondary">
                <a className="navbar-brand nav justify-content-center" href="/">AGME</a>
                <ul class="nav justify-content-right nav nav-pills">
                    <li class="nav-item">
                        <a className="navbar-brand nav justify-content-center" href="/sign-in">Sign in</a>
                    </li>
                    <li class="nav-item">
                        <a className="navbar-brand nav justify-content-center" href="/sign-up">Sign up</a>
                    </li>
                </ul>
            </nav>
        </div>
    );
}

export default Header;