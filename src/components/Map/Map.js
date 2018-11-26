import cytoscape from 'cytoscape';
import React, { createRef, PureComponent } from 'react';

export class MapComponent extends PureComponent {
  mapGraphRef = createRef();
  mapGraph;

  componentDidMount() {
    this.renderMapGraph();
  }

  renderMapGraph = () => {
    this.mapGraph = cytoscape({
      container: this.mapGraphRef.current,
    });
  };

  render() {
    return <div className={this.props.className} ref={this.mapGraphRef} />;
  }
}
