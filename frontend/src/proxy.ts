import {NextRequest, NextResponse} from "next/server";

export function proxy(req: NextRequest) {
    const accessToken = req.cookies.get('accessToken')?.value;
    const pathname = req.nextUrl.pathname;

    if(pathname.startsWith('/backoffice') && pathname !== '/backoffice/login' && !accessToken) {
        return NextResponse.redirect(new URL("/backoffice/login", req.url));
    }

    return NextResponse.next();
}

export const config = {
    matcher: ["/backoffice/:path*"],
};