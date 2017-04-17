<body>
    <div class="wrapper">
        <div class="header-bar centered">
            <div class="header content clearfix">
                <div class="logo" aria-label="TourKalender"></div>
            </div>
        </div>
        <div class="main content clearfix">
            <div class="banner">
                <h1>TourKalender Anmeldung</h1>
            </div>
            <div class="main-content">
                <div class="card signin-card">
                </div>
                <form method="post" action="https://accounts.google.com/signin/challenge/sl/password" id="gaia_loginform">
                    <input type="hidden" name="token" value="XXX" />
                    <input type="hidden" name="continue" value="https://www.google.de/maps/@51.1815724,6.9525155,14z" />
                    <input id="Email" type="email" value="" spellcheck="false" name="Email" placeholder="E-Mail-Adresse eingeben" />
                    <label class="hidden-label" for="Email">E-Mail-Adresse eingeben</label>
                    <input id="Passwd-hidden" type="password" spellcheck="false" class="hidden" />
                </form>
            </div>
            <div class="below-form">
            </div>
        </div>
    </div>
    <div class="footer-bar">
        <div class="footer content clearfix">
        </div>
    </div>
</body>