import {requireUser} from "@/src/lib";

export default async function backoffice() {
    const user = await requireUser();
    return (
        <div className={''}>
            <p>{user.sub}</p>
            <p>{user.scope}</p>
            <p>{user.iat.toLocaleString()}</p>
            <p>{user.exp.toLocaleString()}</p>
        </div>
    )
}