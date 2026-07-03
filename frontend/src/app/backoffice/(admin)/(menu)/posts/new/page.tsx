import PostForm from "@/src/features/posts/ui/PostForm";
import {createPostAction} from "@/src/features/backoffice/posts/actions";

export default function NewPostPage() {
    return <PostForm initialTitle={""} initialContent={""} initialCategories={[]} action={createPostAction} />
}