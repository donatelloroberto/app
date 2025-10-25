import React, { useState, useEffect } from 'react';
import { Folder, Plus, MoreVertical } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

function CollectionsPage() {
  const navigate = useNavigate();
  const [collections, setCollections] = useState([]);
  const [loading, setLoading] = useState(false);

  // Sample collections data
  const sampleCollections = [
    {
      id: '1',
      name: 'Favorites',
      videoCount: 24,
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=Favorites',
      updatedAt: '2 days ago'
    },
    {
      id: '2',
      name: 'Watch Later',
      videoCount: 12,
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=Watch+Later',
      updatedAt: '1 week ago'
    },
    {
      id: '3',
      name: 'My Playlist',
      videoCount: 45,
      thumbnail: 'https://via.placeholder.com/320x180/333/fff?text=My+Playlist',
      updatedAt: '3 days ago'
    },
  ];

  useEffect(() => {
    // Load collections
    setCollections(sampleCollections);
  }, []);

  const handleCreateCollection = () => {
    // Handle collection creation
    alert('Create new collection feature coming soon!');
  };

  return (
    <div className="p-6">
      <div className="flex items-center justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold mb-2">My Collections</h1>
          <p className="text-gray-400">Organize your videos into collections</p>
        </div>
        <button 
          onClick={handleCreateCollection}
          className="btn btn-primary"
          data-testid="create-collection-button"
        >
          <Plus className="w-5 h-5" />
          New Collection
        </button>
      </div>

      {loading ? (
        <div className="flex items-center justify-center h-64">
          <div className="loading-spinner" />
        </div>
      ) : collections.length === 0 ? (
        <div className="text-center py-20">
          <Folder className="w-16 h-16 text-gray-600 mx-auto mb-4" />
          <h3 className="text-xl font-semibold mb-2">No collections yet</h3>
          <p className="text-gray-400 mb-6">
            Create your first collection to organize your videos
          </p>
          <button onClick={handleCreateCollection} className="btn btn-primary">
            <Plus className="w-5 h-5" />
            Create Collection
          </button>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {collections.map((collection) => (
            <div
              key={collection.id}
              className="bg-zinc-900 rounded-lg overflow-hidden hover:ring-2 hover:ring-red-600 transition-all cursor-pointer"
              onClick={() => navigate(`/collection/${collection.id}`)}
              data-testid={`collection-card-${collection.id}`}
            >
              <div className="relative">
                <img 
                  src={collection.thumbnail} 
                  alt={collection.name}
                  className="w-full h-40 object-cover"
                />
                <div className="absolute top-2 right-2">
                  <button 
                    className="p-2 bg-black/60 hover:bg-black/80 rounded-full transition-colors"
                    onClick={(e) => {
                      e.stopPropagation();
                      // Handle menu
                    }}
                  >
                    <MoreVertical className="w-4 h-4" />
                  </button>
                </div>
              </div>
              <div className="p-4">
                <h3 className="text-lg font-semibold mb-2">{collection.name}</h3>
                <div className="flex items-center justify-between text-sm text-gray-400">
                  <span>{collection.videoCount} videos</span>
                  <span>Updated {collection.updatedAt}</span>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <div className="mt-12 bg-zinc-900 rounded-lg p-6">
        <h3 className="text-lg font-semibold mb-3">About Collections</h3>
        <p className="text-gray-400 text-sm">
          Collections help you organize your videos by category, theme, or any way you prefer.
          You can add videos to multiple collections and manage them easily.
        </p>
      </div>
    </div>
  );
}

export default CollectionsPage;
