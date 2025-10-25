# Here are your Instructions

## Known build error: unsupported lucide-react icons

- **Error message**: `Attempted import error: 'FolderVideo' is not exported from 'lucide-react'` (or similar) during `yarn build`.
- **Exact locations**:
  - `frontend/src/components/Sidebar.js`: Sidebar navigation imports `FolderVideo` for the Collections link.
  - `frontend/src/components/CollectionsPage.js`: Empty state imports `FolderVideo` for the placeholder illustration.
- **Quick fix**: Replace the unsupported `FolderVideo` import with the available `Folder` icon in both files, update the JSX to use `<Folder />`, then rerun `yarn build`.
