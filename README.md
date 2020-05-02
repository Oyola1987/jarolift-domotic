# jarolift domotic for raspberry pi

My idea is to manage and centralize all the control of electric blinds.

This service allows you to manage [jarolift](https://www.jarolift.de/rollladenmotoren.html) electric shutters from any browser.

![Diagram](https://app.diagrams.net/?lightbox=1&highlight=0000ff&edit=_blank&layers=1&nav=1&title=Untitled%20Diagram.drawio#R1VbbjtMwEP2aPC7KHXiEdruAQKy2SCy8ufFsYsmJI8e57dczaexcu2rhgcJTPWcu9sw5HcXyNmlzJ0mefBEUuOXatLG8reW6jhe4%2BNMhbY%2B89v0eiCWjOmgE9uwZNGhrtGQUilmgEoIrls%2FBSGQZRGqGESlFPQ97Enx%2Ba05iWAH7iPA1%2Bp1RlfTom8Ae8Q%2FA4sTc7NjakxITrIEiIVTUE8i7tbyNFEL1p7TZAO%2BGZ%2BbS5%2B1e8A4Pk5CpSxLCO%2Brk0efnb4dd%2BrMiu90N5zeanYrwUjdsuSHHeu8pq%2FAYd8dPRArOnpRx4R0T74mEB0iFgovDNyJTeIPxHOQydlnhOE7VGo6kKDMKXZs2uuuEKdjnJOq8NaoSsUSlHC1nyK5AKmhenKQz8IPCBpGCki2G6ITQcKw17RiK61EhA5ZM1RFqkGhVxkPtkTg8aO5%2Bg0dvxePXXIlIlDnIq8%2FLC%2F%2B5efkndL%2BYEmT0XbdA0Io4KQoWzQeDzcv2UQ%2FxaPzojFeBMbfN1LltjdUw9Whq4HmShdaY1Bkmp38c0NWuWlCADYhSRnBeKYrIGNS5zbCmdEJZcIIxg0ngRLFq%2FtxTLOob7gXDRgbF%2BIt%2FmO8shNC3qbOmS29ZKJgX8paK6uewKnQU1dD2n%2BssuJ7OLtfMWS1419QCkj%2Bj0LX%2FUy28XWnhgRT5AeRxmPcfr76nhx38N%2FY0muO3Tz%2Fi8QvSu%2F0F)

Start service with docker 

`docker run --rm --privileged -p 8080:8080 -ti oyola/jarolift-domotic`