import React from "react";
import {Milkdown, MilkdownProvider, useEditor} from "@milkdown/react";

import "@milkdown/crepe/theme/common/style.css";
import "@milkdown/crepe/theme/frame.css"
import {Crepe} from "@milkdown/crepe";
import {uploadImage} from "@/src/features/editor/editorjs/api";

interface Props {
    initialMarkdown: string,
    handleChange: (markdown:string) => void,
}

const CrepeEditor: React.FC<Props> = ({initialMarkdown, handleChange}: Props) => {
    const {loading} = useEditor((root) => {
        const crepe = new Crepe({
            root,
            defaultValue:initialMarkdown,
            features: {
                [Crepe.Feature.ImageBlock]: true,
            },
            featureConfigs: {
                [Crepe.Feature.ImageBlock]: {
                    onUpload: async (file) => {
                        const formData = new FormData();
                        formData.append('image', file);
                        try {
                            return await uploadImage(formData);
                        } catch (error) {
                            console.error(error);
                            return '';
                        }
                    }
                }
            },
        })

        crepe.on(api => {
            api.markdownUpdated((ctx, markdown, prevMarkdown) => {
                handleChange(markdown)
            })
        })

        return crepe;
    })

    return <Milkdown />
}


export default function MilkdownEditor({initialMarkdown, handleChange}: Props): React.ReactElement {
    return (
        <MilkdownProvider>
            <CrepeEditor initialMarkdown={initialMarkdown} handleChange={handleChange}/>
        </MilkdownProvider>
    )
}